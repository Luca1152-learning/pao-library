package pao.library.api.dao;

import pao.library.api.model.Borrow;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class BorrowDao extends Dao<Borrow, Integer> {
    @Override
    public Borrow get(Integer id) {
        Borrow borrow = null;

        String sql = "SELECT * FROM borrows WHERE borrow_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int bookId = resultSet.getInt("book_Id");
                Date borrowDate = resultSet.getDate("borrow_date");
                Date returnDueDate = resultSet.getDate("return_due_date");
                Date returnDate = resultSet.getDate("return_date");

                borrow = new Borrow(id, userId, bookId, borrowDate, returnDueDate, returnDate);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrow;
    }

    @Override
    public Collection<Borrow> getAll() {
        Collection<Borrow> borrows = new ArrayList<>();

        String sql = "SELECT * FROM borrows";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int bookId = resultSet.getInt("book_Id");
                Date borrowDate = resultSet.getDate("borrow_date");
                Date returnDueDate = resultSet.getDate("return_due_date");
                Date returnDate = resultSet.getDate("return_date");

                Borrow borrow = new Borrow(id, userId, bookId, borrowDate, returnDueDate, returnDate);
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrows;
    }

    @Override
    public Integer save(Borrow borrow) throws SQLException {
        if (borrow == null) {
            throw new RuntimeException("The borrow to save shouldn't be null");
        }

        Integer generatedId = null;

        String sql = "INSERT INTO borrows(user_id, book_id, borrow_date, return_due_date, return_date) VALUES (?, ?, "
                + "?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, borrow.getUserId());
            statement.setInt(2, borrow.getBookId());
            statement.setDate(3, borrow.getBorrowDate());
            statement.setDate(4, borrow.getReturnDueDate());
            statement.setDate(5, borrow.getReturnDate());

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
    public void update(Borrow borrow) throws SQLException {
        if (borrow == null) {
            throw new RuntimeException("The borrow to update shouldn't be null");
        }

        String sql = "UPDATE borrows SET user_id=?, book_id=?, borrow_date=?, return_due_date=?, " +
                "return_date=? WHERE borrow_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, borrow.getUserId());
            statement.setInt(2, borrow.getBookId());
            statement.setDate(3, borrow.getBorrowDate());
            statement.setDate(4, borrow.getReturnDueDate());
            statement.setDate(5, borrow.getReturnDate());
            statement.setInt(6, borrow.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Borrow borrow) {
        if (borrow == null) {
            throw new RuntimeException("The borrow to delete shouldn't be null");
        }

        String sql = "DELETE FROM borrows WHERE borrow_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, borrow.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
