package com.kpaw.sakilaspringbootrest.web.model.dtos;

import com.kpaw.sakilaspringbootrest.domain.location.Address;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class CustomerDTO {


    private Short customerId;

    private Byte storeId;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;

    private Boolean active;

    private Timestamp createDate;

    private Date lastUpdate;

    public CustomerDTO(){

    }

    public CustomerDTO(Short customerId){
        this.customerId = customerId;
    }

    public CustomerDTO(Short customerId, Byte storeId, String firstName, String lastName, String email, Address address, Boolean active, Timestamp createDate, Date lastUpdate) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(storeId, that.storeId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(active, that.active) && Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, storeId, firstName, lastName, email, address, active, createDate, lastUpdate);
    }
}
