package pao.library.api.dao;

import pao.library.api.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryDao extends Dao<Category, Integer> {
    @Override
    public Category get(Integer id) {
        Category category = null;

        String sql = "SELECT * FROM categories WHERE category_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public Collection<Category> getAll() {
        Collection<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Integer save(Category category) throws SQLException {
        if (category == null) {
            throw new RuntimeException("The category to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO categories(name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
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
    public void update(Category category) throws SQLException {
        if (category == null) {
            throw new RuntimeException("The category to update shouldn't be null");
        }

        String sql = "UPDATE categories SET name=? WHERE category_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Category category) {
        if (category == null) {
            throw new RuntimeException("The category to delete shouldn't be null");
        }

        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
