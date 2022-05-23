package pao.library.api.service;

import pao.library.api.dao.BookDao;
import pao.library.api.dao.BorrowDao;
import pao.library.api.model.Book;
import pao.library.api.model.Borrow;

import java.sql.SQLException;

public class BorrowService {
    private static final BorrowDao BORROW_DAO = new BorrowDao();
    private static final BookDao BOOK_DAO = new BookDao();

    public static void scheduleBookBorrow(int userId, int bookId) {
        Book book = BookService.getBookById(bookId);

        // Make sure there's at least one available copy
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies to borrow.");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        try {
            BOOK_DAO.update(book);
            BORROW_DAO.save(new Borrow(userId, bookId, null, null));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
