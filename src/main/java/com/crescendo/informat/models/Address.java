package com.crescendo.informat.models;

/** Address POJO
 * @author Groep 5
 */
public class Address {
    private String street;
    private String number;
    private String alphaNumber;
    private String poBox;
    private String postalCode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String street, String number, String alphaNumber, String poBox, String postalCode, String city, String country) {
        this.street = street;
        this.number = number;
        this.alphaNumber = alphaNumber;
        this.poBox = poBox;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAlphaNumber() {
        return alphaNumber;
    }

    public void setAlphaNumber(String alphaNumber) {
        this.alphaNumber = alphaNumber;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", alphaNumber='" + alphaNumber + '\'' +
                ", poBox='" + poBox + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
