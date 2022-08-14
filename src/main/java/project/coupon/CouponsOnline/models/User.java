package project.coupon.CouponsOnline.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String name;
	private String email;
	private String password;
	@Lob
	//@Column(nullable=false)
	private byte[] profile_Image;
	@Enumerated(EnumType.STRING)
	private Usertype userType=Usertype.Client;
	//boolean client;
	   
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		//this.client = client;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", email=" + email + ", password=" + password + ", userType="
				+ userType + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	//One To Many with review
	@OneToMany(mappedBy = "user_review")
	List<Review> review;

	//One To Many with invoice
	@OneToMany(mappedBy = "invoice_user")
	List<Invoice> invoice;
	
/*	//Many to Many with coupon
	@ManyToMany(mappedBy = "user_coupon")
	List<Coupon> coupon;
	*/


	public Usertype getUserType() {
		return userType;
	}

	public void setUserType(Usertype userType) {
		this.userType = userType;
	}

	public Long getId() {
		return Id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getProfile_Image() {
		return profile_Image;
	}

	public void setProfile_Image(byte[] profile_Image) {
		this.profile_Image = profile_Image;
	}
/*
	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}*/

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

/*	public List<Coupon> getCoupon() {
		return coupon;
	}

	public void setCoupon(List<Coupon> coupon) {
		this.coupon = coupon;
	}*/
	
	

}
