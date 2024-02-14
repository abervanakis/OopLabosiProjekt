package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Item extends NamedEntity implements Serializable {
    private Category category;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal lenght;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Discount discountAmount;

    public Item(String name, Long id, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, Discount discountAmount) {
        super(name, id);
        this.category = category;
        this.width = width;
        this.height = height;
        this.lenght = lenght;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discountAmount = discountAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLenght() {
        return lenght;
    }

    public void setLenght(BigDecimal lenght) {
        this.lenght = lenght;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Discount getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCategory(), item.getCategory()) && Objects.equals(getWidth(), item.getWidth()) && Objects.equals(getHeight(), item.getHeight()) && Objects.equals(getLenght(), item.getLenght()) && Objects.equals(getProductionCost(), item.getProductionCost()) && Objects.equals(getSellingPrice(), item.getSellingPrice()) && Objects.equals(getDiscountAmount(), item.getDiscountAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategory(), getWidth(), getHeight(), getLenght(), getProductionCost(), getSellingPrice(), getDiscountAmount());
    }

    @Override
    public String toString() {
        return "Item{" +
                "name=" + super.getName() +
                ", category=" + category.getName() +
                ", width=" + width +
                ", height=" + height +
                ", lenght=" + lenght +
                ", productionCost=" + productionCost +
                ", sellingPrice=" + sellingPrice +
                ", discountAmount=" + discountAmount.discountAmount() +
                '}';
    }
}
