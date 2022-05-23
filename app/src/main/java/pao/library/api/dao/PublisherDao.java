package pao.library.api.dao;

import pao.library.api.model.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class PublisherDao extends Dao<Publisher, Integer> {
    @Override
    public Publisher get(Integer id) {
        Publisher publisher = null;

        String sql = "SELECT * FROM publishers WHERE publisher_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                publisher = new Publisher(id, name);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publisher;
    }

    @Override
    public Collection<Publisher> getAll() {
        Collection<Publisher> publishers = new ArrayList<>();

        String sql = "SELECT * FROM publishers";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("publisher_id");
                String name = resultSet.getString("name");

                Publisher publisher = new Publisher(id, name);
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publishers;
    }

    @Override
    public Integer save(Publisher publisher) throws SQLException {
        if (publisher == null) {
            throw new RuntimeException("The publisher to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO publishers(name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, publisher.getName());
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
    public void update(Publisher publisher) throws SQLException {
        if (publisher == null) {
            throw new RuntimeException("The publisher to update shouldn't be null");
        }

        String sql = "UPDATE publishers SET name=? WHERE publisher_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, publisher.getName());
            statement.setInt(2, publisher.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Publisher publisher) {
        if (publisher == null) {
            throw new RuntimeException("The publisher to delete shouldn't be null");
        }

        String sql = "DELETE FROM publishers WHERE publisher_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, publisher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
