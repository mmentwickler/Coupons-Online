package project.coupon.CouponsOnline.cronjobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import project.coupon.CouponsOnline.models.Coupon;
import project.coupon.CouponsOnline.services.Couponservice;

@Configuration
@EnableScheduling
public class Expiredeals {
	
	@Autowired
	Couponservice couponService;
	
	public void checkExpireCoupon(){
	  	Calendar cal = Calendar.getInstance();
      	Date today = cal.getTime();
      	cal.add(Calendar.MINUTE, 1);
      	Date expiryDate = cal.getTime();
	 // 	System.out.println("For Expiry Date ::  - {}"+ expiryDate);

     	
      	List<Coupon> listcoupon=couponService.findByExpiryDate(true,expiryDate);
    	if(listcoupon!=null){
    		for(Coupon c:listcoupon){
    			c.setActive(false);
    			couponService.updateCoupon(c);
    			
    			System.out.println("Coupon's date : "+c.getExpiry_Date()+" Status : "+c.isActive());
     	    	
    		  	 
    		}
    	}
         	
		
    	
	}
	
    @Scheduled(cron=" 0 * * * * ? ")
	public void check_Expiry(){
    	
    	checkExpireCoupon();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
      	Date date=new Date();
	  	System.out.println("Cron Task :: Execution Time - {}"+ dateFormat.format(date));
	}
	
}
