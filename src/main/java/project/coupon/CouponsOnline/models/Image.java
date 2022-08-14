package project.coupon.CouponsOnline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Lob
	private byte[] image;

	// Many To One with coupon
	@ManyToOne
	@JoinColumn(name = "coupon_fk")
	Coupon coupon_image;
	
	

	public Image(byte[] image, Coupon coupon_image) {
		super();
		this.image = image;
		this.coupon_image = coupon_image;
	}


	public Long getId() {
		return Id;
	}


	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] Image) {
		this.image = Image;
	}

	public Coupon getCoupon_image() {
		return coupon_image;
	}

	public void setCoupon_image(Coupon coupon_image) {
		this.coupon_image = coupon_image;
	}
	
	

}
