package project.coupon.CouponsOnline.models;

/*
@Data*/
public class ChargeRequest {
	   public enum Currencies {
	        EUR, USD, MDL;
	    }
	    private String description;
	    private int amount;
	    private Currencies currency;
	    private String stripeEmail;
	    private String stripeToken;
	    
	    
		public ChargeRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ChargeRequest(String description, int amount, Currencies currency, String stripeEmail,
				String stripeToken) {
			super();
			this.description = description;
			this.amount = amount;
			this.currency = currency;
			this.stripeEmail = stripeEmail;
			this.stripeToken = stripeToken;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public Currencies getCurrency() {
			return currency;
		}
		public void setCurrency(Currencies currency) {
			this.currency = currency;
		}
		public String getStripeEmail() {
			return stripeEmail;
		}
		public void setStripeEmail(String stripeEmail) {
			this.stripeEmail = stripeEmail;
		}
		public String getStripeToken() {
			return stripeToken;
		}
		public void setStripeToken(String stripeToken) {
			this.stripeToken = stripeToken;
		}
	    
	    


}
