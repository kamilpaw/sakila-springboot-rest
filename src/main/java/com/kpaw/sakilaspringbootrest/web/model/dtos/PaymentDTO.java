package com.kpaw.sakilaspringbootrest.web.model.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class PaymentDTO {


    private Short paymentId;

    private CustomerDTO customerDTO;

    private Byte staffId;

    private RentalDTO rentalDTO;

    private BigDecimal amount;

    private Timestamp paymentDate;

    private Date lastUpdate;

    public PaymentDTO(){

    }

    public PaymentDTO(Short paymentId, CustomerDTO customerDTO, Byte staffId, RentalDTO rentalDTO, BigDecimal amount, Timestamp paymentDate) {
        this.paymentId = paymentId;
        this.customerDTO = customerDTO;
        this.staffId = staffId;
        this.rentalDTO = rentalDTO;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public PaymentDTO(Short paymentId, CustomerDTO customerDTO, Byte staffId, RentalDTO rentalDTO, BigDecimal amount, Timestamp paymentDate, Date lastUpdate) {
        this.paymentId = paymentId;
        this.customerDTO = customerDTO;
        this.staffId = staffId;
        this.rentalDTO = rentalDTO;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate;
    }

    public Short getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Short paymentId) {
        this.paymentId = paymentId;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Byte getStaffId() {
        return staffId;
    }

    public void setStaffId(Byte staffId) {
        this.staffId = staffId;
    }

    public RentalDTO getRentalDTO() {
        return rentalDTO;
    }

    public void setRentalDTO(RentalDTO rentalDTO) {
        this.rentalDTO = rentalDTO;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
