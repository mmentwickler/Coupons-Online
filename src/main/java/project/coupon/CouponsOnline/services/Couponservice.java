package project.coupon.CouponsOnline.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.coupon.CouponsOnline.models.Coupon;
import project.coupon.CouponsOnline.repositories.Couponrepository;

@Service
public class Couponservice {

	@Autowired
	Couponrepository couponRepository;

	public Coupon saveCoupon(Coupon c) {
		return couponRepository.save(c);
	}
	
	
	public Coupon updateCoupon(Coupon coupon){
		return couponRepository.saveAndFlush(coupon);
	}
	

	public Coupon findCouponByID(Long id) {
        
	return couponRepository.findById(id).orElse(null);
	
    }
	
	public void delete_Coupon(Coupon c){
		 couponRepository.delete(c);
	}
	
	
	public List<Coupon> findByActive(boolean active) {
        
		return couponRepository.findByActive(active);
		
	    }
	
	
	public List<Coupon> findMainPagecoupon(boolean active){
		return couponRepository.find6LatestCoupons(active,PageRequest.of(0, 6));
		
		//return couponRepository.findTop6ByOrderByNumberAndActiveDesc(active);
	}
	
	  public Page<Coupon> getPaginatedArticles(Pageable pageable) {
	        return couponRepository.findAllByOrderByNumberDesc(pageable);
	    }
	
	
	public List<Coupon> findAll() {

		return couponRepository.findAllByOrderByNumberDesc();

	}
	
	public List<Coupon> findByExpiryDate(boolean active,Date date) {

		return couponRepository.findByActiveAndExpirydateBefore(active,date);

	}
	
	public List<Coupon> findByCategory(String category) {

		return couponRepository.findByCategory(category);

	}
	
		

}
