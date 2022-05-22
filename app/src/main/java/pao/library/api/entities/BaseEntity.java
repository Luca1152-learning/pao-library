package pao.library.api.entities;

public class BaseEntity<K> {
    protected final K id;

    public BaseEntity(K id) {
        this.id = id;
    }

    public K getId() {
        return id;
    }
}
