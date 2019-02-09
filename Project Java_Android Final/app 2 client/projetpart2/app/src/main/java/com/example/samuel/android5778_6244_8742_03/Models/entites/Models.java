package com.example.samuel.android5778_6244_8742_03.Models.entites;

/**
 * Created by samuel on 02-Nov-17.
 */
public class Models {
    protected String model;
    protected String manufacturer;
    protected String name;
    protected float engineCapacity;
    protected gearbox _gearbox;
    protected int placenumbers;
    protected int doornumbers;
    protected boolean luggage;
    protected boolean gps;
    protected boolean airConditionner;


    public Models() {
    }

    @Override
    public String toString() {
        return "Models -->" +
                "model='" + model + "\n" +
                " manufacturer='" + manufacturer + "\n" +
                " name='" + name + "\n" +
                " engineCapacity=" + engineCapacity +
                " _gearbox=" + _gearbox +
                " placenumbers=" + placenumbers +
                " doornumbers=" + doornumbers +"\n" +
                " luggage=" + luggage +
                " gps=" + gps +
                " airConditionner=" + airConditionner ;
    }

    public Models(String model, String manufacturer, String name, float engineCapacity, int placenumbers, int doornumbers, boolean luggage, boolean gps, boolean airConditionner) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.name = name;
        this.engineCapacity = engineCapacity;
        this.placenumbers = placenumbers;
        this.doornumbers = doornumbers;
        this.luggage = luggage;
        this.gps = gps;
        this.airConditionner = airConditionner;
    }

    public Models(String model, String manufacturer, String name, float engineCapacity, int placenumbers, int doornumbers, boolean luggage, boolean gps,
                  boolean airConditionner , gearbox gb) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.name = name;
        this.engineCapacity = engineCapacity;
        this._gearbox = gb;
        this.placenumbers = placenumbers;
        this.doornumbers = doornumbers;
        this.luggage = luggage;
        this.gps = gps;
        this.airConditionner = airConditionner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public gearbox get_gearbox() {
        return _gearbox;
    }

    public void set_gearbox(gearbox _gearbo) {
        this._gearbox = _gearbo;
    }

    public int getPlacenumbers() {
        return placenumbers;
    }

    public void setPlacenumbers(int placenumbers) {
        this.placenumbers = placenumbers;
    }


    public int getDoornumbers() {
        return doornumbers;
    }

    public void setDoornumbers(int doornumbers) {
        this.doornumbers = doornumbers;
    }

    public boolean isLuggage() {
        return luggage;
    }

    public void setLuggage(boolean luggage) {
        this.luggage = luggage;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isAirConditionner() {
        return airConditionner;
    }

    public void setAirConditionner(boolean airConditionner) {
        this.airConditionner = airConditionner;
    }
}
