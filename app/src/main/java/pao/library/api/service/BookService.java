package pao.library.api.service;

import pao.library.api.dao.*;
import pao.library.api.model.*;

import java.sql.SQLException;
import java.util.Collection;

public class BookService {
    private static final BookDao BOOK_DAO = new BookDao();
    private static final AuthorDao AUTHOR_DAO = new AuthorDao();
    private static final CategoryDao CATEGORY_DAO = new CategoryDao();
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

    public static void deleteAllBookAuthors(int bookId) {
        Collection<Integer> bookAuthorsIds = BOOK_AUTHOR_DAO.getAll().stream()
                .filter(bookAuthor -> bookAuthor.getBookId() == bookId)
                .map(BookAuthor::getAuthorId).toList();

        bookAuthorsIds.forEach(authorId -> BOOK_AUTHOR_DAO.delete(new BookAuthor(bookId, authorId)));
    }

    public static Collection<String> getBookCategories(int bookId) {
        Collection<Integer> bookCategoriesIds = BOOK_CATEGORY_DAO.getAll().stream()
                .filter(bookCategory -> bookCategory.getBookId() == bookId)
                .map(BookCategory::getCategoryId).toList();

        return CATEGORY_DAO.getAll().stream()
                .filter(category -> bookCategoriesIds.contains(category.getId()))
                .map(Category::getName)
                .toList();
    }

    public static void deleteAllBookCategories(int bookId) {
        Collection<Integer> bookCategoriesIds = BOOK_CATEGORY_DAO.getAll().stream()
                .filter(bookCategory -> bookCategory.getBookId() == bookId)
                .map(BookCategory::getCategoryId).toList();

        bookCategoriesIds.forEach(categoryId -> BOOK_CATEGORY_DAO.delete(new BookCategory(bookId, categoryId)));
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

    public static void updateBook(Book book) throws SQLException {
        BOOK_DAO.update(book);
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
