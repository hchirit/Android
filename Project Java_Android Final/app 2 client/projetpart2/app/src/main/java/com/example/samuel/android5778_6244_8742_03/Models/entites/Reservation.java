package com.example.samuel.android5778_6244_8742_03.Models.entites;

/**
 * Created by samuel on 02-Nov-17.
 */
public class Reservation {

    protected int reservation_Number;
    protected int id_client;
    protected boolean status;
    protected int cars_number;
    protected String dateBegining;
    protected String dateEnd;
    protected float kmBegin;
    protected float kmEnd;
    protected boolean gasStatus;
    protected float gasLiter;
    protected float bills;

    public Reservation() {
    }

    public Reservation(int reservation_Number, int id_client, boolean status, int cars_number, String dateBegining, String dateEnd, float kmBegin, float kmEnd, boolean gasStatus, float gasLiter, float bills) {
        this.reservation_Number = reservation_Number;
        this.id_client = id_client;
        this.status = status;
        this.cars_number = cars_number;
        this.dateBegining = dateBegining;
        this.dateEnd = dateEnd;
        this.kmBegin = kmBegin;
        this.kmEnd = kmEnd;
        this.gasStatus = gasStatus;
        this.gasLiter = gasLiter;
        this.bills = bills;
    }

    public int getReservation_Number() {
        return reservation_Number;
    }

    public void setReservation_Number(int reservation_Number) {
        this.reservation_Number = reservation_Number;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCars_number() {
        return cars_number;
    }

    public void setCars_number(int cars_number) {
        this.cars_number = cars_number;
    }

    public String getDateBegining() {
        return dateBegining;
    }

    public void setDateBegining(String dateBegining) {
        this.dateBegining = dateBegining;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public float getKmBegin() {
        return kmBegin;
    }

    public void setKmBegin(float kmBegin) {
        this.kmBegin = kmBegin;
    }

    public float getKmEnd() {
        return kmEnd;
    }

    public void setKmEnd(float kmEnd) {
        this.kmEnd = kmEnd;
    }

    public boolean isGasStatus() {
        return gasStatus;
    }

    public void setGasStatus(boolean gasStatus) {
        this.gasStatus = gasStatus;
    }

    public float getGasLiter() {
        return gasLiter;
    }

    public void setGasLiter(float gasLiter) {
        this.gasLiter = gasLiter;
    }

    public float getBills() {
        return bills;
    }

    public void setBills(float bills) {
        this.bills = bills;
    }
}
