package project.coupon.CouponsOnline.controllers;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import project.coupon.CouponsOnline.config.PaypalPaymentIntent;
import project.coupon.CouponsOnline.config.PaypalPaymentMethod;
import project.coupon.CouponsOnline.models.Coupon;
import project.coupon.CouponsOnline.models.Invoice;
import project.coupon.CouponsOnline.services.Couponservice;
import project.coupon.CouponsOnline.services.Invoiceservice;
import project.coupon.CouponsOnline.services.PaypalService;
import project.coupon.CouponsOnline.services.Userservice;
import project.coupon.CouponsOnline.utils.URLUtils;

@Controller
@RequestMapping("/")
public class PaymentController {
	@Autowired
	Couponservice couponService;
	@Autowired
	Userservice userService;
	@Autowired 
	Invoiceservice invoiceService;

	public static final String PAYPAL_SUCCESS_URL = "success";
	public static final String PAYPAL_CANCEL_URL = "cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@RequestMapping(method = RequestMethod.GET, value = "paypal")
	public String index() {
		return "index1";
	}

	@RequestMapping(method = RequestMethod.GET, value = "pay")
	public String pay(HttpServletRequest request) {
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;

		Coupon c = (Coupon) request.getSession().getAttribute("show_single_coupon");
		/*
		 * String cancelUrl = "paypalapi/" + PAYPAL_CANCEL_URL; String
		 * successUrl = "paypalapi/" + PAYPAL_SUCCESS_URL;
		 */
		try {
			Payment payment = paypalService.createPayment(
					Double.valueOf(c.getCoupon_Price()),
					"USD",
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale, 
					c.getDescription(),
					cancelUrl,
					successUrl);
			
			
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay() {
		return "cancel";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			HttpServletRequest request, Model model) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
			//	User user = (User) request.getSession().getAttribute("user");
				Coupon c = (Coupon) request.getSession().getAttribute("show_single_coupon");
				c.setTotal_Sell(c.getTotal_Sell() + 1);
				couponService.updateCoupon(c);
				Invoice invoice=new Invoice();
				invoice.setPayerId(payerId);
				invoice.setPaymentId(paymentId);
				invoiceService.saveInvoice(invoice);
				request.getSession().setAttribute("coupon_code","cun"+invoice.getId());
				Coupon soldcoupon = couponService.findCouponByID(c.getId());
		//		request.getSession().setAttribute("user", userService.findByEmail(user.getEmail()));
				request.getSession().setAttribute("showcoupon",soldcoupon);
					
				//model.addAttribute("showcoupon", soldcoupon);

				return "coupon";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

}