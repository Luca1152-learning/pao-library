package pao.library.api.service;

import pao.library.api.dao.AuthorDao;
import pao.library.api.dao.BookAuthorDao;
import pao.library.api.dao.BookCategoryDao;
import pao.library.api.dao.BookDao;
import pao.library.api.model.*;

import java.sql.SQLException;
import java.util.Collection;

public class BookService {
    private static final BookDao BOOK_DAO = new BookDao();
    private static final AuthorDao AUTHOR_DAO = new AuthorDao();
    private static final BookAuthorDao BOOK_AUTHOR_DAO = new BookAuthorDao();
    private static final BookCategoryDao BOOK_CATEGORY_DAO = new BookCategoryDao();

    public static Collection<Book> getAllBooks() {
        return BOOK_DAO.getAll();
    }

    public static Collection<String> getBookAuthors(int bookId) {
        Collection<Integer> bookAuthorsIds = BOOK_AUTHOR_DAO.getAll().stream()
                .filter(bookAuthor -> bookAuthor.getBookId() == bookId)
                .map(BookAuthor::getAuthorId).toList();

        return AUTHOR_DAO.getAll().stream()
                .filter(author -> bookAuthorsIds.contains(author.getId()))
                .map(Author::getName)
                .toList();
    }

    public static Book getBookById(int bookId) {
        return BOOK_DAO.get(bookId);
    }

    public static void deleteBookById(int bookId) {
        BOOK_DAO.delete(BOOK_DAO.get(bookId));
    }

    public static int addBook(Book book) throws SQLException {
        return BOOK_DAO.save(book);
    }

    public static void addBookAuthors(int bookId, Collection<Author> authors) {
        authors.forEach(author -> {
            try {
                BOOK_AUTHOR_DAO.save(new BookAuthor(bookId, author.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static void addBookCategories(int bookId, Collection<Category> categories) {
        categories.forEach(category -> {
            try {
                BOOK_CATEGORY_DAO.save(new BookCategory(bookId, category.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
