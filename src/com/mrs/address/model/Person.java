package com.mrs.address.model;

import javafx.beans.property.*;

import java.time.LocalDate;


/**
 * Created by marus505 on 2017. 4. 24..
 */
public class Person {

    private final StringProperty firstName;

    private final StringProperty lastName;

    private final StringProperty street;

    private final IntegerProperty postalCode;

    private final StringProperty city;

    private final ObjectProperty<LocalDate> birthDay;

    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        //dummy
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthDay = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public LocalDate getBirthDay() {
        return birthDay.get();
    }

    public ObjectProperty<LocalDate> birthDayProperty() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay.set(birthDay);
    }
}
