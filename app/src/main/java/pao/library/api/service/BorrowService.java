package pao.library.api.service;

import pao.library.api.dao.BookDao;
import pao.library.api.dao.BorrowDao;
import pao.library.api.model.Book;
import pao.library.api.model.Borrow;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public class BorrowService {
    private static final BorrowDao BORROW_DAO = new BorrowDao();
    private static final BookDao BOOK_DAO = new BookDao();

    public static Collection<Borrow> getAllBorrows() {
        return BORROW_DAO.getAll();
    }

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

    public static Collection<Borrow> getBorrowRequests() {
        return BORROW_DAO.getAll().stream().filter(borrow -> borrow.getBorrowDate() == null).toList();
    }

    public static Collection<Borrow> getBorrowsToBeReturned() {
        return BORROW_DAO.getAll().stream().filter(borrow -> borrow.getBorrowDate() != null && borrow.getReturnDate() == null).toList();
    }

    public static void satisfyBorrowRequest(int borrowId) {
        Borrow borrow = BORROW_DAO.get(borrowId);

        // Set the borrow date to today
        borrow.setBorrowDate(new Date(System.currentTimeMillis()));
        try {
            BORROW_DAO.update(borrow);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void receiveBook(int borrowId) {
        Borrow borrow = BORROW_DAO.get(borrowId);

        // Set the return date to today
        borrow.setReturnDate(new Date(System.currentTimeMillis()));
        try {
            BORROW_DAO.update(borrow);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
