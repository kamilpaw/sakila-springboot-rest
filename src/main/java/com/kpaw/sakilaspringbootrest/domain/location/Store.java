package com.kpaw.sakilaspringbootrest.domain.location;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte storeId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff managerStaff;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    public Store(){

    }

    public Store(Byte storeId, Staff managerStaff, Address address) {
        this.storeId = storeId;
        this.managerStaff = managerStaff;
        this.address = address;
    }

    public Store(Byte storeId, Staff managerStaff, Address address, Date lastUpdate) {
        this.storeId = storeId;
        this.managerStaff = managerStaff;
        this.address = address;
        this.lastUpdate = lastUpdate;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public Staff getManagerStaff() {
        return managerStaff;
    }

    public void setManagerStaff(Staff managerStaffId) {
        this.managerStaff = managerStaffId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addressId) {
        this.address = addressId;
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
                ", managerStaffId=" + managerStaff +
                ", addressId=" + address +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
