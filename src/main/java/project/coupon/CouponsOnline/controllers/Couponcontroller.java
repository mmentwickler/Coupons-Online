package project.coupon.CouponsOnline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import project.coupon.CouponsOnline.models.Coupon;
import project.coupon.CouponsOnline.models.Image;
import project.coupon.CouponsOnline.models.User;
import project.coupon.CouponsOnline.services.Couponservice;
import project.coupon.CouponsOnline.services.Imageservice;

@Controller
@RequestMapping("/Coupons")
public class Couponcontroller {

	@Autowired
	Couponservice couponService;
	@Autowired
	Imageservice imageService;

	@RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
	public RedirectView addCoupon(Coupon coupon, RedirectAttributes redirectAttrs, HttpServletRequest request,
			@RequestParam("mainImage") MultipartFile mainImage, @RequestParam("couponImg") MultipartFile couponImage,
			@RequestParam("more_images") MultipartFile[] more_images) {

		System.out.println("Test....." + more_images);

		try {
			coupon.setMain_Image(mainImage.getBytes());
			coupon.setCoupon_Image(couponImage.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		coupon.setActive(true);
		coupon.setNumber(couponService.findAll().size());
		couponService.saveCoupon(coupon);

		try {
			for (MultipartFile file : more_images) {
				Image image = new Image(file.getBytes(), coupon);
				imageService.saveImage(image);
				// List<Image> couponImages=coupon.getImages();
				List<Image> couponImages = coupon.getImages();
				if (couponImages == null) {
					couponImages = new ArrayList<Image>();
				}
				couponImages.add(image);
				coupon.setImages(couponImages);
				couponService.updateCoupon(coupon);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new RedirectView("/faq", true);

	}
	//updateCoupon

	@RequestMapping("/open_deal_single/{id}")
	public RedirectView deal_single(@PathVariable Long id, HttpServletRequest request) {
		Coupon c = couponService.findCouponByID(id);
		// System.out.println(c);
		request.getSession().setAttribute("single_coupon_session", c);
		request.getSession().setAttribute("single_coupon_img_session", c.getImages());
		return new RedirectView("/deal_single", true);

	}

	
	@RequestMapping("/edit_coupon/{id}")
	public RedirectView editCoupon(@PathVariable Long id, HttpServletRequest request) {
		Coupon c = couponService.findCouponByID(id);
		request.getSession().setAttribute("edit_coupon", c);
		return new RedirectView("/edit_coupon_model", true);

	}
	
	
	@RequestMapping("/delete_coupon/{id}")
	public RedirectView deleteCoupon(@PathVariable Long id, HttpServletRequest request) {
		Coupon c = couponService.findCouponByID(id);
		couponService.delete_Coupon(c);
		return new RedirectView("/manage_coupons", true);

	}

	@RequestMapping("/showCoupon/{id}")
	public RedirectView show_Coupon(@PathVariable Long id, HttpServletRequest request) {
		Coupon c = couponService.findCouponByID(id);
		// boolean loggedin=false;
		if (null != request.getSession().getAttribute("loggedin")) {
			User user = (User) request.getSession().getAttribute("user");
			// if(user.getCoupon().equals(c))

			request.getSession().setAttribute("show_single_coupon", c);
			if (c.getCoupon_Price() == 0) {
				return new RedirectView("/coupon", true);
			} else {
				return new RedirectView("/pay", true);
			}
		}

		else {
			return new RedirectView("/signin", true);

		}

	}

	@RequestMapping("/findByCategory/{name}")
	public RedirectView findByCategory(@PathVariable String name, HttpServletRequest request) {
		List<Coupon> c = couponService.findByCategory(name);
		// System.out.println(c);
		request.getSession().setAttribute("couponByCat", c);
		return new RedirectView("/couponsbyCategory", true);

	}
	
	
	@RequestMapping("/printcoupon")
	public String printCoupon() {
	/*	List<Coupon> c = couponService.findByCategory(name);
		// System.out.println(c);
		request.getSession().setAttribute("couponByCat", c);
	*/	return "print";

	}



}
