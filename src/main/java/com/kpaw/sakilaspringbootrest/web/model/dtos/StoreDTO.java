package com.kpaw.sakilaspringbootrest.web.model.dtos;

import com.kpaw.sakilaspringbootrest.domain.location.Address;

import java.util.Date;

public class StoreDTO {


    private Byte storeId;

    private Byte managerId;

    private Address address;

    private Date lastUpdate;

    public StoreDTO() {

    }

    public StoreDTO(Byte storeId, Byte managerId, Address address) {
        this.storeId = storeId;
        this.managerId = managerId;
        this.address = address;
    }

    public StoreDTO(Byte storeId, Byte managerId, Address address, Date lastUpdate) {
        this.storeId = storeId;
        this.managerId = managerId;
        this.address = address;
        this.lastUpdate = lastUpdate;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public Byte getManagerId() {
        return managerId;
    }

    public void setManagerId(Byte managerId) {
        this.managerId = managerId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
