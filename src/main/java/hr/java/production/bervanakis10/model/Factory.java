package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Factory extends NamedEntity implements Serializable {
    private Address address;
    private Set<Item> items;

    public Factory(String name, Long id, Address address, Set<Item> items) {
        super(name, id);
        this.address = address;
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        if (!(o instanceof Factory factory)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAddress(), factory.getAddress()) && Objects.equals(getItems(), factory.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getItems());
    }
}
