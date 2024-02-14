package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Apple extends Item implements Edible, Serializable {

    private static final Integer KilocaloriesPerKilogram = 500;
    private BigDecimal weight;
    public Apple(String name, Long id,Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, Discount discountAmount) {
        super(name, id, category, width, height, lenght, productionCost, sellingPrice, discountAmount);
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * Metoda koja računa kalorije
     *
     * @return vrijednost izračunatih kalorija
     */
    @Override
    public Integer calculateKilocalories() {
        return KilocaloriesPerKilogram;
    }

    /**
     * Izračunava ukupnu cijenu na temelju dane težine i prodajne cijene po kilogramu.
     *
     * @param weight               Težina predmeta.
     * @param sellingPricePerKilogram Prodajna cijena po kilogramu predmeta.
     * @return Ukupna cijena predmeta izračunata množenjem težine s prodajnom cijenom po kilogramu.
     */
    @Override
    public BigDecimal calculatePrice(BigDecimal weight, BigDecimal sellingPricePerKilogram) {
        return sellingPricePerKilogram.multiply(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apple apple)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getWeight(), apple.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeight());
    }
}
