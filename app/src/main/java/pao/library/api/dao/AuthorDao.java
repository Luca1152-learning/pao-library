package pao.library.api.dao;

import pao.library.api.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class AuthorDao extends Dao<Author, Integer> {
    @Override
    public Author get(Integer id) {
        Author author = null;

        String sql = "SELECT * FROM authors WHERE author_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                author = new Author(id, name);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return author;
    }

    @Override
    public Collection<Author> getAll() {
        Collection<Author> authors = new ArrayList<>();

        String sql = "SELECT * FROM authors";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("author_id");
                String name = resultSet.getString("name");

                Author author = new Author(id, name);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public Integer save(Author author) throws SQLException {
        if (author == null) {
            throw new RuntimeException("The author to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO authors(name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getName());
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
    public void update(Author author) throws SQLException {
        if (author == null) {
            throw new RuntimeException("The author to update shouldn't be null");
        }

        String sql = "UPDATE authors SET name=? WHERE author_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getName());
            statement.setInt(2, author.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Author author) {
        if (author == null) {
            throw new RuntimeException("The author to delete shouldn't be null");
        }

        String sql = "DELETE FROM authors WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
