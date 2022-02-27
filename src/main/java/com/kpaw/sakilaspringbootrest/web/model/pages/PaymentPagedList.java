package com.kpaw.sakilaspringbootrest.web.model.pages;

import com.kpaw.sakilaspringbootrest.web.model.dtos.PaymentDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PaymentPagedList extends PageImpl<PaymentDTO> {
    public PaymentPagedList(List<PaymentDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

}
