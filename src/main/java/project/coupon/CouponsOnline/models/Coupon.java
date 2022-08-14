package project.coupon.CouponsOnline.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "tab", initialValue = 0)
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String title;
	private String category;
	private String description;
	private float coupon_Price;
	private float deal_Old_Price;
	private float deal_New_Price;
	private String how_To_Use;
	private String other_Details;
	//@DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
	private Date expirydate;
	private boolean active;
	@Lob
	@Column(nullable=false)
	private byte[] main_Image;
	@Lob
	@Column(nullable=false)
	private byte[] coupon_Image;
	private float rating;
	private int total_Sell;
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int number;
	boolean client;
	// One To Many with review
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "coupon_review")
	List<Review> review;

	// One To Many with image
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "coupon_image")
	List<Image> images;
	
	//One To Many with invoice
		@OneToMany(fetch = FetchType.LAZY,mappedBy = "invoice_coupon")
		List<Invoice> invoice;

	/*// Many to Many with user
	@ManyToMany
	@JoinTable(name = "user_coupon_table", joinColumns = @JoinColumn(name = "coupon_column"), inverseJoinColumns = @JoinColumn(name = "user_column"))
	List<User> user_coupon;*/
	
	

	public Coupon(String category, String description, float coupon_Price, float deal_Old_Price, float deal_New_Price,
			String how_To_Use, String other_Details, Date expiry_Date, boolean active, byte[] main_Image, float rating,
			int total_Sell, int number, boolean client) {
		super();
		this.category = category;
		this.description = description;
		this.coupon_Price = coupon_Price;
		this.deal_Old_Price = deal_Old_Price;
		this.deal_New_Price = deal_New_Price;
		this.how_To_Use = how_To_Use;
		this.other_Details = other_Details;
		this.expirydate = expiry_Date;
		this.active = active;
		this.main_Image = main_Image;
		this.rating = rating;
		this.total_Sell = total_Sell;
		this.number = number;
		this.client = client;
	}
	
	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

	public byte[] getCoupon_Image() {
		return coupon_Image;
	}

	public void setCoupon_Image(byte[] coupon_Image) {
		this.coupon_Image = coupon_Image;
	}

	public Long getId() {
		return Id;
	}

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCoupon_Price() {
		return coupon_Price;
	}

	public void setCoupon_Price(float coupon_Price) {
		this.coupon_Price = coupon_Price;
	}

	public float getDeal_Old_Price() {
		return deal_Old_Price;
	}

	public void setDeal_Old_Price(float deal_Old_Price) {
		this.deal_Old_Price = deal_Old_Price;
	}

	public float getDeal_New_Price() {
		return deal_New_Price;
	}

	public void setDeal_New_Price(float deal_New_Price) {
		this.deal_New_Price = deal_New_Price;
	}

	public String getHow_To_Use() {
		return how_To_Use;
	}

	public void setHow_To_Use(String how_To_Use) {
		this.how_To_Use = how_To_Use;
	}

	public String getOther_Details() {
		return other_Details;
	}

	public void setOther_Details(String other_Details) {
		this.other_Details = other_Details;
	}

	public Date getExpiry_Date() {
		return expirydate;
	}

	public void setExpiry_Date(Date expiry_Date) {
		this.expirydate = expiry_Date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte[] getMain_Image() {
		return main_Image;
	}

	public void setMain_Image(byte[] main_Image) {
		this.main_Image = main_Image;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getTotal_Sell() {
		return total_Sell;
	}

	public void setTotal_Sell(int total_Sell) {
		this.total_Sell = total_Sell;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

/*	public List<User> getUser_coupon() {
		return user_coupon;
	}

	public void setUser_coupon(List<User> user_coupon) {
		this.user_coupon = user_coupon;
	}*/

	@Override
	public String toString() {
		return "Coupon [Id=" + Id + ", title=" + title + ", category=" + category + ", description=" + description
				+ ", coupon_Price=" + coupon_Price + ", deal_Old_Price=" + deal_Old_Price + ", deal_New_Price="
				+ deal_New_Price + ", how_To_Use=" + how_To_Use + ", other_Details=" + other_Details + ", expiry_Date="
				+ expirydate + ", active=" + active + ", main_Image=" + Arrays.toString(main_Image) + ", coupon_Image="
				+ Arrays.toString(coupon_Image) + ", rating=" + rating + ", total_Sell=" + total_Sell + ", number="
				+ number + ", client=" + client + ", review=" + review + ", images=" + images + "]";
	}

}
