package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Store extends NamedEntity implements Serializable {
    private String webAddress;
    private Set<Item> items;

    public Store(String name, Long id, String webAddress, Set<Item> items) {
        super(name, id);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store store)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getWebAddress(), store.getWebAddress()) && Objects.equals(getItems(), store.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWebAddress(), getItems());
    }
}
