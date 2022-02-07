package com.kpaw.sakilaspringbootrest.domain.location;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_id")
    private Byte storeId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff managerStaffId;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressId;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    public Store(){

    }

    public Store(Byte storeId, Staff managerStaffId, Address addressId, Date lastUpdate) {
        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
        this.lastUpdate = lastUpdate;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public Staff getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(Staff managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", managerStaffId=" + managerStaffId +
                ", addressId=" + addressId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
