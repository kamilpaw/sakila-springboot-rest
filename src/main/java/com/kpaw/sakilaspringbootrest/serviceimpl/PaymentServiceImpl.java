package com.kpaw.sakilaspringbootrest.serviceimpl;

import com.kpaw.sakilaspringbootrest.domain.rent.Payment;
import com.kpaw.sakilaspringbootrest.exception.EntityNotFoundExc;
import com.kpaw.sakilaspringbootrest.repository.rental.PaymentRepository;
import com.kpaw.sakilaspringbootrest.service.PaymentService;
import com.kpaw.sakilaspringbootrest.web.mapper.DTOMapper;
import com.kpaw.sakilaspringbootrest.web.model.dtos.PaymentDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.PaymentPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private DTOMapper mapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, DTOMapper mapper){
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }

    @Override
    public PaymentPagedList findAll(PageRequest pageRequest) {
        Page<Payment> paymentPage = paymentRepository.findAll(pageRequest);
        return new PaymentPagedList(paymentPage.getContent().stream().map(mapper::toPaymentDto).collect(Collectors.toList()),
                PageRequest.of(paymentPage.getPageable().getPageNumber(), paymentPage.getPageable().getPageSize()),
                paymentPage.getTotalElements());
    }

    @Override
    public void save(PaymentDTO paymentDTO) {
        paymentRepository.save(mapper.toPayment(paymentDTO));
    }

    @Override
    public PaymentDTO findById(int id) {
        Optional<Payment> result = paymentRepository.findById((short) id);
        if (result.isEmpty()){
            throw new EntityNotFoundExc("payment", id);
        }
        return mapper.toPaymentDto(result.get());
    }

    @Override
    public void deleteById(int id) {
        paymentRepository.deleteById((short) id);
    }
}
