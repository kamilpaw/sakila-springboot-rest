package com.kpaw.sakilaspringbootrest.web.model.pages;

import com.kpaw.sakilaspringbootrest.domain.rent.Rental;
import com.kpaw.sakilaspringbootrest.web.model.dtos.RentalDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RentalPagedList extends PageImpl<RentalDTO> {
    public RentalPagedList(List<RentalDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RentalPagedList(List<RentalDTO> content) {
        super(content);
    }
}
