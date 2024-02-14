package hr.java.production.bervanakis10.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public record Discount(BigDecimal discountAmount) implements Serializable {

    public Discount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Metoda koja dohvaća vrijednost popusta
     *
     * @return vraća vrijednost popusta
     */
    @Override
    public BigDecimal discountAmount() {
        return discountAmount;
    }

    /**
     * Izračunava cijenu s primijenjenim popustom na temelju osnovne cijene i postotka popusta.
     *
     * @param price          Osnovna cijena prije primjene popusta.
     * @param discountAmount Postotak popusta izražen kao vrijednost od 0 do 1 (npr., 0.1 za 10% popusta).
     * @return Cijena s primijenjenim popustom.
     */
    public BigDecimal calculatePriceWithDiscount(BigDecimal price, BigDecimal discountAmount) {
        return price.subtract(price.multiply(discountAmount.multiply(BigDecimal.valueOf(0.01))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount discount)) return false;
        return Objects.equals(discountAmount, discount.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount);
    }
}
