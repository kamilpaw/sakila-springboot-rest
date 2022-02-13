package com.kpaw.sakilaspringbootrest.web.mapper;

import com.kpaw.sakilaspringbootrest.domain.location.Inventory;
import com.kpaw.sakilaspringbootrest.domain.location.Staff;
import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import com.kpaw.sakilaspringbootrest.service.ActorService;
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
    ActorService actorService;

    @Autowired
    DTOMapper(StoreService storeService, FilmService filmService, ActorService actorService) {
        this.storeService = storeService;
        this.filmService = filmService;
        this.actorService = actorService;
    }

    public StaffDTO toStaffDTO(Staff staff) {

        return new StaffDTO(staff.getStaffId(), staff.getFirstName(), staff.getLastName(), staff.getAddres(), staff.getPicture(),
                staff.getEmail(), staff.getStore().getStoreId(), staff.getActive(), staff.getUsername(), staff.getPassword(),
                staff.getLastUpdate());
    }

    public Staff toStaff(StaffDTO staffDTO) {
        return new Staff(staffDTO.getStaffId(), staffDTO.getFirstName(), staffDTO.getLastName(), staffDTO.getAddresId(), staffDTO.getPicture(),
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

}
