package pao.library.api.model;

import java.util.Objects;

public class Publisher extends BaseEntity<Integer> {
    private String name;

    public Publisher(int id, String name) {
        super(id);
        this.name = name;
    }

    public Publisher(String name) {
        super(null);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher[name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return name.equals(publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
