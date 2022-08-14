package project.coupon.CouponsOnline.services;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import project.coupon.CouponsOnline.config.PaypalPaymentIntent;
import project.coupon.CouponsOnline.config.PaypalPaymentMethod;

@Service
public class PaypalService {

	@Autowired
	private APIContext apiContext;
	@Autowired
	Invoiceservice invoiceService;
	private Long invoiceId;
	
	//APIContext context = new APIContext();
	
	public Payment createPayment(
			Double total, 
			String currency, 
			PaypalPaymentMethod method, 
			PaypalPaymentIntent intent, 
			String description, 
			String cancelUrl, 
			String successUrl) throws PayPalRESTException{
		Amount amount = new Amount();
		amount.setCurrency(currency);
		total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		amount.setTotal(String.format("%.2f", total));
		
		//Long Invoiceid;
		/*
		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}
*/
		
	/*	Invoice invoice=new Invoice();
		System.out.println(invoice.getId());
		invoiceService.saveInvoice(invoice);
		invoiceId=invoice.getId();
*/	/*	if(invoiceId==null){
		Invoice invoice=invoiceService.	
		}*/
		
		
		Transaction transaction = new Transaction();
		transaction.setDescription(description);
		transaction.setAmount(amount);
	//	transaction.setInvoiceNumber(invoiceId.toString());

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod(method.toString());

		Payment payment = new Payment();
		payment.setIntent(intent.toString());
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		//payment.set
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);
	//	apiContext.setMaskRequestId("101");
	//	payment.set
		apiContext.setMaskRequestId(true);
		return payment.create(apiContext);
	}
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
		Payment payment = new Payment();
		payment.setId(paymentId);
	//	paymen
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecute);
	}
}
