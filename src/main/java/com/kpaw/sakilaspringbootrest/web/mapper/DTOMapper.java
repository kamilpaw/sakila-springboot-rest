package com.kpaw.sakilaspringbootrest.web.mapper;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.service.FilmService;
import com.kpaw.sakilaspringbootrest.service.StoreService;
import com.kpaw.sakilaspringbootrest.web.model.CustomerDTO;
import com.kpaw.sakilaspringbootrest.web.model.InventoryDTO;
import com.kpaw.sakilaspringbootrest.web.model.StaffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    StoreService storeService;
    FilmService filmService;

    @Autowired
    DTOMapper(StoreService storeService, FilmService filmService) {
        this.storeService = storeService;
        this.filmService = filmService;
    }

    public StaffDTO toStaffDTO(Staff staff) {

        return new StaffDTO(staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getAddresId(), staff.getPicture(),
                staff.getEmail(), staff.getStoreId().getStoreId(), staff.getActive(), staff.getUsername(), staff.getPassword(),
                staff.getLastUpdate());
    }

    public Staff toStaff(StaffDTO staffDTO) {
        return new Staff(staffDTO.getStaffId(), staffDTO.getFirstName(), staffDTO.getLastName(), staffDTO.getAddresId(), staffDTO.getPicture(),
                staffDTO.getEmail(), storeService.finById(staffDTO.getStoreId()), staffDTO.getActive(), staffDTO.getUsername(),
                staffDTO.getPassword(), staffDTO.getLastUpdate());
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getStoreId().getStoreId(),
                customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                customer.getAddressId(), customer.getActive(), customer.getCreateDate(),
                customer.getLastUpdate());
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getCustomerId(), storeService.finById(customerDTO.getStoreId()),
                customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(),
                customerDTO.getAddressId(), customerDTO.getActive(), customerDTO.getCreateDate(),
                customerDTO.getLastUpdate());
    }

    public InventoryDTO toInventoryDto(Inventory inventory) {
        return new InventoryDTO(inventory.getInventoryId(), inventory.getFilmId().getFilmId(), inventory.getStoreId().getStoreId(), inventory.getLastUpdate());
    }

    public Inventory toInventory(InventoryDTO inventoryDTO) {
        return new Inventory(inventoryDTO.getInventoryId(), filmService.findByID(inventoryDTO.getFilmId()), storeService.finById(inventoryDTO.getStoreId()),
                inventoryDTO.getLastUpdate());
    }
}
