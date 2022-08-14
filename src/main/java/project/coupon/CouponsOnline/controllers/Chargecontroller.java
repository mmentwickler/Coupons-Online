package project.coupon.CouponsOnline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import project.coupon.CouponsOnline.models.ChargeRequest;
import project.coupon.CouponsOnline.models.ChargeRequest.Currencies;
import project.coupon.CouponsOnline.services.StripeService;

@Controller
public class Chargecontroller {

	@Autowired
	private StripeService paymentsService;
	
	@RequestMapping("/chargetest")
	public String testcharge(ChargeRequest chargeRequest, Model model) throws StripeException {

			return "payment_request_test";
	}

	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {

		chargeRequest.setDescription("Example charge");
		chargeRequest.setCurrency(Currencies.EUR);

		Charge charge = paymentsService.charge(chargeRequest);
		model.addAttribute("id", charge.getId());
		model.addAttribute("status", charge.getStatus());
		model.addAttribute("chargeId", charge.getId());
		model.addAttribute("balance_transaction", charge.getBalanceTransaction());
		return "result";
	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}

}
