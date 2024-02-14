package hr.java.production.bervanakis10.model;

import java.math.BigDecimal;

public interface Edible {
    Integer calculateKilocalories();
    BigDecimal calculatePrice(BigDecimal weight, BigDecimal sellingPricePerKilogram);
}
