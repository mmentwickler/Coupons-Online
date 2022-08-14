package project.coupon.CouponsOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.coupon.CouponsOnline.models.Invoice;

public interface Invoicerepository extends JpaRepository<Invoice,Long> {

}
