package project.coupon.CouponsOnline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.coupon.CouponsOnline.models.Invoice;
import project.coupon.CouponsOnline.repositories.Invoicerepository;

@Service
public class Invoiceservice {

	@Autowired
	Invoicerepository invoiceRepository;

	public Invoice saveInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);

	}

	public Invoice findInvoiceByID(Long id) {

		return invoiceRepository.findById(id).orElse(null);

	}

}
