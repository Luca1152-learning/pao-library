package pao.library.api.dao;

import pao.library.api.model.BookCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class BookCategoryDao extends Dao<BookCategory, Integer> {
    @Override
    public BookCategory get(Integer id) {
        throw new RuntimeException("Link tables do not have an id.");
    }

    @Override
    public Collection<BookCategory> getAll() {
        Collection<BookCategory> booksCategories = new ArrayList<>();

        String sql = "SELECT * FROM books_categories";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                int categoryId = resultSet.getInt("category_id");

                BookCategory bookCategory = new BookCategory(bookId, categoryId);
                booksCategories.add(bookCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksCategories;
    }

    @Override
    public Integer save(BookCategory bookCategory) {
        if (bookCategory == null) {
            throw new RuntimeException("The book category instance to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO books_categories(book_id, category_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, bookCategory.getBookId());
            statement.setInt(2, bookCategory.getCategoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public void update(BookCategory bookCategory) {
        throw new RuntimeException("Entries in link tables can't be updated");
    }

    @Override
    public void delete(BookCategory bookCategory) {
        if (bookCategory == null) {
            throw new RuntimeException("The book category instance to delete shouldn't be null");
        }

        String sql = "DELETE FROM books_categories WHERE book_id = ? AND category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, bookCategory.getBookId());
            statement.setInt(2, bookCategory.getCategoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
