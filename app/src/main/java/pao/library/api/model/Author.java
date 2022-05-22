package pao.library.api.model;

import java.util.Objects;

public class Author extends BaseEntity<Integer> {
    private final String name;

    public Author(int id, String name) {
        super(id);
        this.name = name;
    }

    public Author(String name) {
        super(null);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author[name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
