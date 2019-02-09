package com.example.samuel.android5778_6244_8742_03.Models.entites;

/**
 * Created by samuel on 02-Nov-17.
 */
public class Address {

    public String city;
    public String street;
    public String Number;


    public Address(String city, String street, String phoneNumber) {
        this.city = city;
        this.street = street;
        this.Number = phoneNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", Number='" + Number + '\'' +
                '}';
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return Number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.Number = phoneNumber;
    }



}
