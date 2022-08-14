package project.coupon.CouponsOnline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private int rating;
	private String review;
	//Many To One with coupon
	@ManyToOne
    @JoinColumn(name = "coupon_fk" )
    Coupon coupon_review;
  
    //Many To One with user
    @ManyToOne
    @JoinColumn(name = "user_fk" )
    User user_review;
    
 

	public Review(int rating, String review, Coupon coupon_review, User user_review) {
		super();
		this.rating = rating;
		this.review = review;
		this.coupon_review = coupon_review;
		this.user_review = user_review;
	}



	public Long getId() {
		return Id;
	}

	

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Coupon getCoupon_review() {
		return coupon_review;
	}

	public void setCoupon_review(Coupon coupon_review) {
		this.coupon_review = coupon_review;
	}

	public User getUser_review() {
		return user_review;
	}

	public void setUser_review(User user_review) {
		this.user_review = user_review;
	}
  
	
}
