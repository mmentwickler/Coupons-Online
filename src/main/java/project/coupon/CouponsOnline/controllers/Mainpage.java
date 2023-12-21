package project.coupon.CouponsOnline.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.coupon.CouponsOnline.models.Coupon;
import project.coupon.CouponsOnline.models.Image;
import project.coupon.CouponsOnline.models.User;
import project.coupon.CouponsOnline.models.Usertype;
import project.coupon.CouponsOnline.services.Couponservice;
import project.coupon.CouponsOnline.services.Imageservice;

@Controller
@RequestMapping("/")
public class Mainpage {

	@Autowired
	Couponservice couponService;
	@Autowired
	Imageservice imageService;

	@Autowired
	private JavaMailSender javaMailSender;

	void sendEmailfunc(String name, String email, String other, String mesg) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("abc@gmail.com");
		msg.setFrom(other);
		msg.setSubject(name);
		msg.setText(other + " " + mesg);

		javaMailSender.send(msg);

	}
	
	public boolean loggedin(HttpServletRequest request){
	/*	request.getSession().setAttribute("loggedin", true);
		request.getSession().setAttribute("user", userService.findByEmail(user.getEmail()));
	*/	boolean yes=false;

	  if(null != request.getSession().getAttribute("loggedin")){
		yes=true;
	  }else{
		     yes=false;
		}
	  return yes;
	}
	
	public boolean admin(HttpServletRequest request){
	/*	request.getSession().setAttribute("loggedin", true);
		request.getSession().setAttribute("user", userService.findByEmail(user.getEmail()));
	*/	boolean yes=false;

	  if(null != request.getSession().getAttribute("user")){
		  User user=(User)request.getSession().getAttribute("user");
		if(user.getUserType().equals(Usertype.Admin))
			{yes=true;}
		else
			{yes=false;}
	  }else{
		     yes=false;
		}
	  return yes;
	}
	
	public boolean client(HttpServletRequest request){
	/*	request.getSession().setAttribute("loggedin", true);
		request.getSession().setAttribute("user", userService.findByEmail(user.getEmail()));
	*/	boolean yes=false;

	  if(null != request.getSession().getAttribute("user")){
		  User user=(User)request.getSession().getAttribute("user");
		if(user.getUserType().equals(Usertype.Client))
			{yes=true;}
		else
			{yes=false;}
	  }else{
		     yes=false;
		}
	  return yes;
	}

	/*
	 * public boolean admin(){ request.getSession().setAttribute("loggedin",
	 * true); request.getSession().setAttribute("user",
	 * userService.findByEmail(user.getEmail()));
	 * 
	 * }
	 */

	@RequestMapping("/")
	public String homePage(Model model, HttpServletRequest request) {

		List<Coupon> coupons = couponService.findMainPagecoupon(true);
		model.addAttribute("coupons", coupons);
		// request.getSession().setAttribute("couponsall",couponService.findAll());
		return "index";
	}

	@RequestMapping("/edit_coupon_model")
	public String editCoupon(Model model, HttpServletRequest request) {
		model.addAttribute("editcoupon", request.getSession().getAttribute("edit_coupon"));
		return "edit_coupon";
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public String sendEmail(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("othercontact") String othercontact, @RequestParam("msg") String msg) {
		System.out.println(name + email + othercontact + msg);
		try {
			sendEmailfunc(name, email, othercontact, msg);
		} catch (Exception e) {
		}
		return "contact_us_01";
	}

	@RequestMapping(value = "/main_page_coupons", method = RequestMethod.GET)
	@ResponseBody
	public String getEventCount(Model model) {
		System.out.println("testing ajax call");
		model.addAttribute("coupons", couponService.findMainPagecoupon(true));
		return "index";
	}

	@RequestMapping(value = "/coupon", method = RequestMethod.GET)
	public String showCoupon(Model model, HttpServletRequest request) {
		Coupon c = (Coupon) request.getSession().getAttribute("show_single_coupon");
		// System.out.println(c.toString());
		request.getSession().setAttribute("showcoupon", c);
		return "coupon";

	}

	@RequestMapping(value = "/all_active_coupons", method = RequestMethod.GET)
	@ResponseBody
	public String all_active_Coupons(Model model) {
		System.out.println("testing ajax call.....all_deals");
		model.addAttribute("active_coupons", couponService.findByActive(true));
		return "all_deals_list";
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void loadImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); // Or
																				// whatever
																				// format
																				// you
																				// wanna
																				// use
		System.out.println("test image controller");
		Coupon coupon = couponService.findCouponByID(Long.valueOf(id));
		response.getOutputStream().write(coupon.getMain_Image());

		response.getOutputStream().close();

	}

	private byte[] add_code(String c_code, Coupon coupon) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(coupon.getCoupon_Image());
		BufferedImage bImage2 = ImageIO.read(bis);
		Graphics graphics = bImage2.getGraphics();
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 200, 30);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
		graphics.drawString(c_code, 10, 25);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bImage2, "jpg", baos);
		byte[] data1 = baos.toByteArray();

		return data1;
	}

	@RequestMapping(value = "/couponimage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void couponImage(@PathVariable String id, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); // Or
																				// whatever
																				// format
																				// you
																				// wanna
																				// use
		System.out.println("test image controller");
		Coupon coupon = couponService.findCouponByID(Long.valueOf(id));
		if (null == request.getSession().getAttribute("coupon_code")) {
			response.getOutputStream().write(coupon.getCoupon_Image());
		} else {
			String c_code = (String) request.getSession().getAttribute("coupon_code");
			byte[] image_with_code = add_code(c_code, coupon);
			response.getOutputStream().write(image_with_code);

		}

		response.getOutputStream().close();

	}

	@RequestMapping(value = "/deal_single_img/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void loadDealSingleImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); // Or
																				// whatever
																				// format
																				// you
																				// wanna
																				// use
		System.out.println("test image controller");
		Image image = imageService.findImgByID(Long.valueOf(id));
		response.getOutputStream().write(image.getImage());

		response.getOutputStream().close();

		/*
		 * InputStream is = new ByteArrayInputStream(coupon.getMain_Image());
		 * IOUtils.copy(is, response.getOutputStream());
		 */

	}

	@RequestMapping("/signin")
	public String signInPage(User user) {
		return "signin";
	}

	@RequestMapping("/checkexpiry")
	public String checkexpiry() {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.MINUTE, 1);
		Date expiryDate = cal.getTime();
		System.out.println("For Expiry Date ::  - {}" + expiryDate);

		List<Coupon> listcoupon = couponService.findByExpiryDate(true, expiryDate);
		if (listcoupon != null) {
			for (Coupon c : listcoupon) {
				System.out.println("Coupon's date" + c.getExpiry_Date());

			}
		}

		return "index";
	}

	@RequestMapping("/terms_conditions")
	public String terms_conditions() {
		return "terms_conditions";
	}

	@RequestMapping("/faq")
	public String faq() {
		return "faq";
	}

	@RequestMapping("/signup")
	public String signup(User user) {
		// user.setClient(true);
		return "signup";
	}

	@RequestMapping("/profile")
	public String profile(User user) {

		return "profile";
	}

	@RequestMapping("/categories")
	public String categories() {
		return "categories";
	}

	@RequestMapping("/couponsbyCategory")
	public String couponByCategories(Model model, HttpServletRequest request) {
		List<Coupon> c = (List<Coupon>) request.getSession().getAttribute("couponByCat");
		model.addAttribute("coupon_by_cat", c);

		return "Category";
	}

	@RequestMapping("/edit_coupon")
	public String edit_coupon() {
		return "edit_coupon";
	}

	@RequestMapping("/delete_coupon")
	public String delete_coupon() {
		return "delete_coupon";
	}

	@RequestMapping("/add_coupon")
	public String add_coupon(Coupon coupon) {
		return "add_coupon";
	}

	@RequestMapping("/expired_deals")
	public String expired_deals(Model model) {
		// expired_coupons
		model.addAttribute("expired_coupons", couponService.findByActive(false));
		return "expired_deals";
	}

	@RequestMapping("/manage_coupons")
	public String manage_coupons(Model model) {
		// expired_coupons
		model.addAttribute("mng_coupons", couponService.findAll());
		return "coupons_grid";
	}

	@RequestMapping("/contact_us_01")
	public String contact_us_01() {
		return "contact_us_01";
	}

	@RequestMapping("/all_deals_list")
	public String all_deals_list(Model model) {
		model.addAttribute("active_coupons", couponService.findByActive(true));
		return "all_deals_list";
	}

	@RequestMapping("/deal_single")
	public String deal_single(Model model, HttpServletRequest request) {
		Coupon c = (Coupon) request.getSession().getAttribute("single_coupon_session");
		List<Image> listImage = (List<Image>) request.getSession().getAttribute("single_coupon_img_session");
		for (Image img : listImage) {
			System.out.println(img.getId());
		}
		model.addAttribute("single_coupon", c);
		model.addAttribute("single_coupon_img", listImage);

		return "deal_single";
	}

	@RequestMapping("/checkout_payment")
	public String checkout_payment() {
		return "checkout_payment";
	}

}
