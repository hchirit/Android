package com.example.samuel.android5778_6244_8742_03.Models.entites;

/**
 * Created by samuel on 02-Nov-17.
 */
public class Branch {

    //protected String _address;
    protected int parkplace;
    protected int branchNumber;
    protected Address address_;

    public Address getAddress_() {
        return address_;
    }

    public void setAddress_(Address address_) {
        this.address_ = address_;
    }

    public Branch(int parkplace, int branchNumber, Address address_) {
        this.parkplace = parkplace;
        this.branchNumber = branchNumber;
        this.address_ = address_;
    }

    public Branch() {
        
    }

    @Override
    public String toString() {
        return "Branch{" +
                "_address='" +  '\'' +
                ", parkplace=" + parkplace +
                ", branchNumber=" + branchNumber +
                ", address_=" + address_ +
                '}';
    }

    public Branch(String _address, int parkplace, int branchNumber) {

        this.parkplace = parkplace;
        this.branchNumber = branchNumber;
    }




    public int getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    public int getParkplace() {
        return parkplace;
    }

    public void setParkplace(int parkplace) {
        this.parkplace = parkplace;
    }
}
