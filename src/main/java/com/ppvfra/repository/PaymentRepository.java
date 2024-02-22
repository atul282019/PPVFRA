package com.ppvfra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ppvfra.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query("select a.id,a.applicationid, a.paymentmode, a.paymenttype,a.amount,a.ddnumber,a.dddate,a.bankname,a.branchname from Payment a where a.applicationid=:id")
	Payment getPaymentByApplicationid(@Param("id") int id);
}
