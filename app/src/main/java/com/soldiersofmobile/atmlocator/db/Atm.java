package com.soldiersofmobile.atmlocator.db;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "atm", daoClass = AtmDao.class)
public class Atm {

    @DatabaseField(generatedId = true)
    private UUID id;

    @DatabaseField
    private String address;
    @DatabaseField
    private double longitude;
    @DatabaseField
    private double latitude;
    @DatabaseField(foreign = true)
    private Bank bank;



    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(final Bank bank) {
        this.bank = bank;
    }
}
