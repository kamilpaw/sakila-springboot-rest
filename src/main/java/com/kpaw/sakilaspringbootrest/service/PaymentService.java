package com.kpaw.sakilaspringbootrest.service;

import com.kpaw.sakilaspringbootrest.web.model.dtos.PaymentDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.PaymentPagedList;
import org.springframework.data.domain.PageRequest;

public interface PaymentService {


    PaymentPagedList findAll(PageRequest pageRequest);

    void save(PaymentDTO paymentDTO);

    PaymentDTO findById(int id);

    void deleteById(int id);
}
