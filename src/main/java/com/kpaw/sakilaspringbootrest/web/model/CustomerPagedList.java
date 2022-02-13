package com.kpaw.sakilaspringbootrest.web.model;

import com.kpaw.sakilaspringbootrest.domain.rent.Customer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomerPagedList extends PageImpl<CustomerDTO> {
    public CustomerPagedList(List<CustomerDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomerPagedList(List<CustomerDTO> content) {
        super(content);
    }
}
