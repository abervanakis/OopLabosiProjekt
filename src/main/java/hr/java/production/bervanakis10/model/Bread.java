package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Bread extends Item implements Edible, Serializable {

    private static final Integer KilocaloriesPerKilogram = 2500;
    private BigDecimal weight;

    public Bread(String name, Long id, Category category, BigDecimal width, BigDecimal height, BigDecimal lenght, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, Discount discountAmount) {
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
     * Izračunava ukupnu cijenu predmeta na temelju zadane težine i prodajne cijene po kilogramu.
     *
     * @param weight                  Težina predmeta koji se kupuje, izražena u kilogramima.
     * @param sellingPricePerKilogram Prodajna cijena po kilogramu predmeta.
     * @return Ukupna cijena predmeta, izračunata kao produkt težine i prodajne cijene po kilogramu.
     */
    @Override
    public BigDecimal calculatePrice(BigDecimal weight, BigDecimal sellingPricePerKilogram) {
        return sellingPricePerKilogram.multiply(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bread bread)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getWeight(), bread.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeight());
    }
}
