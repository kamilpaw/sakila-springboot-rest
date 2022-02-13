package com.kpaw.sakilaspringbootrest.web.model.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class RentalDTO {


    private Integer rentalId;

    private Timestamp rentalDate;

    private Integer inventoryId;

    private CustomerDTO customerDTO;

    private Timestamp returnDate;

    private Byte staffId;

    private Date lastUpdate;

    public RentalDTO() {

    }

    public RentalDTO(Integer rentalId, Timestamp rentalDate, Integer inventoryId, CustomerDTO customerDTO, Timestamp returnDate, Byte staffId) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerDTO = customerDTO;
        this.returnDate = returnDate;
        this.staffId = staffId;
    }

    public RentalDTO(Integer rentalId, Timestamp rentalDate, Integer inventoryId, CustomerDTO customerDTO, Timestamp returnDate, Byte staffId, Date lastUpdate) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerDTO = customerDTO;
        this.returnDate = returnDate;
        this.staffId = staffId;
        this.lastUpdate = lastUpdate;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Byte getStaffId() {
        return staffId;
    }

    public void setStaffId(Byte staffId) {
        this.staffId = staffId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
