package com.example.nathanmsika.Android5778_6244_8742_01.model.entites;

/**
 * Created by samuel on 02-Nov-17.
 */
public class Client {
    protected String name;
    protected String ID;
    protected String phoneNumber;
    protected String emailAddress;
    protected String password;


    public Client(String name, String ID, String phoneNumber, String emailAddress, String pass) {
        this.name = name;
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = pass;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCreditCardNumbers() {
        return password;
    }

    public void setCreditCardNumbers(String creditCardNumbers) {
        this.password = creditCardNumbers;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
