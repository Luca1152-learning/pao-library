package pao.library.api.model;

import java.util.Objects;

public class BookCategory {
    private int bookId;
    private int categoryId;

    public BookCategory(int bookId, int categoryId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int book_id) {
        this.bookId = book_id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategory that = (BookCategory) o;
        return bookId == that.bookId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, categoryId);
    }
}
