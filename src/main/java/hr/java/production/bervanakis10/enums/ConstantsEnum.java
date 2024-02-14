package hr.java.production.bervanakis10.enums;

public enum ConstantsEnum {
    NUMBER_OF_OBJECTS_CATEGORY(3),
    NUMBER_OF_OBJECTS_ITEM(1),
    NUMBER_OF_OBJECTS_FACTORY(1),
    NUMBER_OF_OBJECTS_STORE(1);
    Integer value;

    ConstantsEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
