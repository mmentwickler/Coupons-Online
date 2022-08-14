package project.coupon.CouponsOnline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription {

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String email;
	
	
	
	public Subscription(String email) {
		super();
		this.email = email;
	}

	public Long getId() {
		return Id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
