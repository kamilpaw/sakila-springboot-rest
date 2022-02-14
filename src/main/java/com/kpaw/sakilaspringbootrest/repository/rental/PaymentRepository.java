package com.kpaw.sakilaspringbootrest.repository.rental;

import com.kpaw.sakilaspringbootrest.domain.rent.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Short> {
}
