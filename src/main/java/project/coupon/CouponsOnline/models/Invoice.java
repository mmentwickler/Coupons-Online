package project.coupon.CouponsOnline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String paymentId;
	private String payerId;
	//Many To One with User
		@ManyToOne
	    @JoinColumn(name = "user_fk" )
	    User invoice_user;
		//Many To One with coupon
		@ManyToOne
	    @JoinColumn(name = "coupon_fk" )
	    Coupon invoice_coupon;
		public Invoice() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}
		public Invoice(String paymentId, String payerId) {
			super();
			this.paymentId = paymentId;
			this.payerId = payerId;
		}
		public String getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(String paymentId) {
			this.paymentId = paymentId;
		}
		public String getPayerId() {
			return payerId;
		}
		public void setPayerId(String payerId) {
			this.payerId = payerId;
		}
	
		public User getInvoice_user() {
			return invoice_user;
		}
		public void setInvoice_user(User invoice_user) {
			this.invoice_user = invoice_user;
		}
		public Coupon getInvoice_coupon() {
			return invoice_coupon;
		}
		public void setInvoice_coupon(Coupon invoice_coupon) {
			this.invoice_coupon = invoice_coupon;
		}
		
		
		

	
}
