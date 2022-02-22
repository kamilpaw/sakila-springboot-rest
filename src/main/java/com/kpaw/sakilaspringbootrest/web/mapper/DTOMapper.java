package com.kpaw.sakilaspringbootrest.web.mapper;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.domain.location.Store;
import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.domain.rent.Payment;
import com.kpaw.sakilaspringbootrest.domain.rent.Rental;
import com.kpaw.sakilaspringbootrest.service.*;
import com.kpaw.sakilaspringbootrest.web.model.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    private final StoreService storeService;
    private final FilmService filmService;
    private final InventoryService inventoryService;
    private final StaffService staffService;

    @Autowired
    DTOMapper(StoreService storeService, FilmService filmService, InventoryService inventoryService,
              StaffService staffService) {
        this.storeService = storeService;
        this.filmService = filmService;
        this.inventoryService = inventoryService;
        this.staffService = staffService;
    }

    public StaffDTO toStaffDTO(Staff staff) {

        return new StaffDTO(staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getAddress(), staff.getPicture(),
                staff.getEmail(), staff.getStore().getStoreId(), staff.getActive(), staff.getUsername(), staff.getPassword(),
                staff.getLastUpdate());
    }

    public Staff toStaff(StaffDTO staffDTO) {
        return new Staff(staffDTO.getStaffId(), staffDTO.getFirstName(), staffDTO.getLastName(), staffDTO.getAddressId(), staffDTO.getPicture(),
                staffDTO.getEmail(), storeService.finById(staffDTO.getStoreId()), staffDTO.getActive(), staffDTO.getUsername(),
                staffDTO.getPassword(), staffDTO.getLastUpdate());
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getStore().getStoreId(),
                customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                customer.getAddress(), customer.getActive(), customer.getCreateDate(),
                customer.getLastUpdate());
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getCustomerId(), storeService.finById(customerDTO.getStoreId()),
                customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(),
                customerDTO.getAddress(), customerDTO.getActive(), customerDTO.getCreateDate(),
                customerDTO.getLastUpdate());
    }

    public InventoryDTO toInventoryDto(Inventory inventory) {
        return new InventoryDTO(inventory.getInventoryId(), inventory.getFilm().getFilmId(), inventory.getStore().getStoreId(), inventory.getLastUpdate());
    }

    public Inventory toInventory(InventoryDTO inventoryDTO) {
        return new Inventory(inventoryDTO.getInventoryId(), filmService.findByID(inventoryDTO.getFilmId()), storeService.finById(inventoryDTO.getStoreId()),
                inventoryDTO.getLastUpdate());
    }

    public RentalDTO toRentalDto(Rental rental) {
        return new RentalDTO(rental.getRentalId(),
                rental.getRentalDate(),
                rental.getInventory().getInventoryId(),
                this.toCustomerDTO(rental.getCustomer()),
                rental.getReturnDate(),
                rental.getStaff().getStaffId(),
                rental.getLastUpdate());
    }

    public Rental toRental(RentalDTO rentalDTO) {
        return new Rental(rentalDTO.getRentalId(),
                rentalDTO.getRentalDate(),
                inventoryService.findById(rentalDTO.getInventoryId()),
                this.toCustomer(rentalDTO.getCustomerDTO()),
                rentalDTO.getReturnDate(),
                staffService.findById(rentalDTO.getStaffId()),
                rentalDTO.getLastUpdate());
    }

    public PaymentDTO toPaymentDto(Payment payment) {
        return new PaymentDTO(payment.getPaymentId(),
                this.toCustomerDTO(payment.getCustomer()),
                payment.getStaff().getStaffId(),
                this.toRentalDto(payment.getRental()),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getLastUpdate());
    }

    public Payment toPayment(PaymentDTO paymentDTO) {
        return new Payment(paymentDTO.getPaymentId(),
                this.toCustomer(paymentDTO.getCustomerDTO()),
                staffService.findById(paymentDTO.getStaffId()),
                this.toRental(paymentDTO.getRentalDTO()),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getLastUpdate());
    }

    public StoreDTO toStoreDTO(Store store){
        return new StoreDTO(store.getStoreId(),store.getManagerStaff().getStaffId(),
                store.getAddress(),store.getLastUpdate());
    }

    public Store toStore(StoreDTO storeDTO){
        return new Store(storeDTO.getStoreId(),
                staffService.findById(storeDTO.getManagerId()),
                storeDTO.getAddress(),
                storeDTO.getLastUpdate());
    }

}
