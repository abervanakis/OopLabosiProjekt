package hr.java.production.bervanakis10.model;

public sealed interface Technical permits Laptop {
    Integer warrantyPeriod();
}
