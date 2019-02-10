package com.example.nathanmsika.Android5778_6244_8742_01.model.entites;

/**
 * Created by samuel on 02-Nov-17.
 */
public class Reservation {
    protected long idNumber;
    protected boolean status;
    protected int carsnumber;
    protected String dateBegining;
    protected String dateEnd;
    protected float kmBegin;
    protected float kmEnd;
    protected boolean gasStatus;
    protected float gasLiter;
    protected float bills;
    protected String reservationNumber;

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCarsnumber() {
        return carsnumber;
    }

    public void setCarsnumber(int carsnumber) {
        this.carsnumber = carsnumber;
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

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
