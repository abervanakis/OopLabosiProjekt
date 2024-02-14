package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public final class Laptop extends Item implements Technical, Serializable {

    private Integer warrantyPeriod;
    public Laptop(String name, Long id, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, Discount discountAmount, Integer warrantyPeriod) {
        super(name, id, category, width, height, lenght, productionCost, sellingPrice, discountAmount);
        this.warrantyPeriod = warrantyPeriod;
    }

    /**
     * Metoda koja vraća rok garancije u mjesecima
     *
     * @return vraća rok garancije u mjesecima
     */
    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public Integer warrantyPeriod() {
        return warrantyPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop laptop)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getWarrantyPeriod(), laptop.getWarrantyPeriod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWarrantyPeriod());
    }
}
