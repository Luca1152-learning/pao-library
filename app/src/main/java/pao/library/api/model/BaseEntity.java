package pao.library.api.model;

public class BaseEntity<K> {
    protected K id;

    public BaseEntity(K id) {
        this.id = id;
    }

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
