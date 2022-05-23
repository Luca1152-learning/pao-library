package pao.library.api.model;

import java.util.Collection;
import java.util.Objects;

public class Book extends BaseEntity<Integer> {
    private String title;
    private String description;
    private int publisherId;
    private int firstPublicationYear;
    private int pagesCount;
    private int availableCopies;

    public Book(int id, String title, String description, int publisherId, int firstPublicationYear, int pagesCount,
                int availableCopies) {
        super(id);
        this.title = title;
        this.description = description;
        this.publisherId = publisherId;
        this.firstPublicationYear = firstPublicationYear;
        this.pagesCount = pagesCount;
        this.availableCopies = availableCopies;
    }

    public Book(String title, String description, int publisherId, int firstPublicationYear, int pagesCount,
                int availableCopies) {
        super(null);
        this.title = title;
        this.description = description;
        this.publisherId = publisherId;
        this.firstPublicationYear = firstPublicationYear;
        this.pagesCount = pagesCount;
        this.availableCopies = availableCopies;
    }

    public void showDetails(Collection<String> authorsNames, String publisherName) {
        System.out.println("Title: " + getTitle());
        System.out.println("Author(s): " + String.join(", ", authorsNames));
        System.out.println("Publication year: " + getFirstPublicationYear());
        System.out.println("Publisher: " + publisherName);
        System.out.println("Description: " + getDescription());
        System.out.println("Pages: " + getPagesCount());
        System.out.println("Available copies: " + getAvailableCopies());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getFirstPublicationYear() {
        return firstPublicationYear;
    }

    public void setFirstPublicationYear(int firstPublicationYear) {
        this.firstPublicationYear = firstPublicationYear;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book[id = " + id + ", title=" + title + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book author = (Book) o;
        return title.equals(author.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
