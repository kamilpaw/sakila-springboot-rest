package com.kpaw.sakilaspringbootrest.web.controller;

import com.kpaw.sakilaspringbootrest.service.PaymentService;
import com.kpaw.sakilaspringbootrest.web.model.dtos.PaymentDTO;
import com.kpaw.sakilaspringbootrest.web.model.pages.PaymentPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.kpaw.sakilaspringbootrest.web.model.pages.PageSizeAndNumber.pageNumber;
import static com.kpaw.sakilaspringbootrest.web.model.pages.PageSizeAndNumber.pageSize;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public PaymentPagedList findAll(@RequestParam(required = false) Integer pageNumber,
                             @RequestParam(required = false) Integer pageSize) {
        return paymentService.findAll(PageRequest.of(pageNumber(pageNumber), pageSize(pageSize)));
    }

    @GetMapping("/payments/{paymentId}")
    public PaymentDTO findById(@PathVariable int paymentId){
        return paymentService.findById(paymentId);
    }

    @PostMapping("/payments")
    public PaymentDTO saveNewPayment(@RequestBody PaymentDTO paymentDTO){
        paymentDTO.setPaymentId(null);
        paymentService.save(paymentDTO);
        return paymentDTO;
    }

    @PutMapping("/payments")
    public PaymentDTO updatePayment(@RequestBody PaymentDTO paymentDTO){
        paymentService.save(paymentDTO);
        return paymentDTO;
    }

    @DeleteMapping("/payments/{paymentId}")
    public String deleteById(@PathVariable int paymentId){
        paymentService.deleteById(paymentId);
        return "Payment with id " + paymentId + " deleted";
    }
}
