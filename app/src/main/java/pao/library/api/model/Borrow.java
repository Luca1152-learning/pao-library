package pao.library.api.model;

import java.sql.Date;
import java.util.Objects;

public class Borrow extends BaseEntity<Integer> {
    private int userId;
    private int bookId;
    private Date borrowDate;
    private Date returnDueDate;
    private Date returnDate;

    public Borrow(int id, int userId, int bookId, Date borrowDate, Date returnDueDate, Date returnDate) {
        super(id);
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDueDate = returnDueDate;
        this.returnDate = returnDate;
    }

    public Borrow(int userId, int bookId, Date borrowDate, Date returnDueDate, Date returnDate) {
        super(null);
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDueDate = returnDueDate;
        this.returnDate = returnDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDueDate() {
        return returnDueDate;
    }

    public void setReturnDueDate(Date returnDueDate) {
        this.returnDueDate = returnDueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return userId == borrow.userId && bookId == borrow.bookId && Objects.equals(borrowDate, borrow.borrowDate) && Objects.equals(returnDueDate, borrow.returnDueDate) && Objects.equals(returnDate, borrow.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId, borrowDate, returnDueDate, returnDate);
    }

    @Override
    public String toString() {
        return "Borrow[id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", borrowDate=" + borrowDate +
                ", returnDueDate=" + returnDueDate + ", returnDate=" + returnDate + ']';
    }
}
