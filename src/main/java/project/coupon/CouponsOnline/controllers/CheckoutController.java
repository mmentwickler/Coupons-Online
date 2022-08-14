package project.coupon.CouponsOnline.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.coupon.CouponsOnline.models.ChargeRequest;

@Controller
public class CheckoutController {
        @Value("${STRIPE_PUBLIC_KEY}")
	   private String stripePublicKey;//=System.getProperty("stripe.api.key.public");

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currencies.EUR);
        return "checkout";
    }
	
}
