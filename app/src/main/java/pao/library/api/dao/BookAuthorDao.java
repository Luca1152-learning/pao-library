package pao.library.api.dao;

import pao.library.api.model.BookAuthor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class BookAuthorDao extends Dao<BookAuthor, Integer> {
    @Override
    public BookAuthor get(Integer id) {
        throw new RuntimeException("Link tables do not have an id.");
    }

    @Override
    public Collection<BookAuthor> getAll() {
        Collection<BookAuthor> booksAuthors = new ArrayList<>();

        String sql = "SELECT * FROM books_authors";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                int authorId = resultSet.getInt("author_id");

                BookAuthor bookAuthor = new BookAuthor(bookId, authorId);
                booksAuthors.add(bookAuthor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksAuthors;
    }

    @Override
    public Integer save(BookAuthor bookAuthor) {
        if (bookAuthor == null) {
            throw new RuntimeException("The book author instance to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO books_authors(book_id, author_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, bookAuthor.getBookId());
            statement.setInt(2, bookAuthor.getAuthorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @Override
    public void update(BookAuthor bookAuthor) {
        throw new RuntimeException("Entries in link tables can't be updated");
    }

    @Override
    public void delete(BookAuthor bookAuthor) {
        if (bookAuthor == null) {
            throw new RuntimeException("The book author instance to delete shouldn't be null");
        }

        String sql = "DELETE FROM books_authors WHERE book_id = ? AND author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, bookAuthor.getBookId());
            statement.setInt(2, bookAuthor.getAuthorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
