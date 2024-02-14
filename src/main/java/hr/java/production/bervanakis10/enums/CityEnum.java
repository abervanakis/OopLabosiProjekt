package hr.java.production.bervanakis10.enums;

public enum CityEnum {
    ZAGREB("Zagreb", "10000"),
    SPLIT("SPLIT", "21000"),
    SAMOBOR("Samobor", "10430");

    private String cityName;
    private String postalCode;

    CityEnum(String cityName, String postalCode) {
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }
    public String getPostalCode() {
        return postalCode;
    }
}
