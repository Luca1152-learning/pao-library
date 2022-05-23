package pao.library.api.dao;

import pao.library.api.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class BookDao extends Dao<Book, Integer> {
    @Override
    public Book get(Integer id) {
        Book book = null;

        String sql = "SELECT * FROM books WHERE book_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("password");
                int publisherId = resultSet.getInt("publisher_id");
                int firstPublicationYear = resultSet.getInt("first_publication_year");
                int pagesCount = resultSet.getInt("pages_count");
                int availableCopies = resultSet.getInt("available_copies");

                book = new Book(id, title, description, publisherId, firstPublicationYear, pagesCount, availableCopies);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public Collection<Book> getAll() {
        Collection<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("password");
                int publisherId = resultSet.getInt("publisher_id");
                int firstPublicationYear = resultSet.getInt("first_publication_year");
                int pagesCount = resultSet.getInt("pages_count");
                int availableCopies = resultSet.getInt("available_copies");

                Book book = new Book(id, title, description, publisherId, firstPublicationYear, pagesCount,
                        availableCopies);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Integer save(Book book) throws SQLException {
        if (book == null) {
            throw new RuntimeException("The book to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO books(title, description, publisher_id, first_publication_year, pages_count, " +
                "available_copies) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getPublisherId());
            statement.setInt(4, book.getFirstPublicationYear());
            statement.setInt(5, book.getPagesCount());
            statement.setInt(6, book.getAvailableCopies());

            int numberOfInstertedRows = statement.executeUpdate();

            // Get the id
            if (numberOfInstertedRows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);
                }
                resultSet.close();
            }
        }

        return generatedId;
    }

    @Override
    public void update(Book book) throws SQLException {
        if (book == null) {
            throw new RuntimeException("The book to update shouldn't be null");
        }

        String sql = "UPDATE books SET title=?, description=?, publisher_id=?, first_publication_year=?, " +
                "pages_count=?, available_copies = ? WHERE book_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getPublisherId());
            statement.setInt(4, book.getFirstPublicationYear());
            statement.setInt(5, book.getPagesCount());
            statement.setInt(6, book.getAvailableCopies());
            statement.setInt(7, book.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Book book) {
        if (book == null) {
            throw new RuntimeException("The book to delete shouldn't be null");
        }

        String sql = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
