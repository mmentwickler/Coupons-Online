package project.coupon.CouponsOnline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.coupon.CouponsOnline.models.Image;
import project.coupon.CouponsOnline.repositories.Imagerepository;

@Service
public class Imageservice {
	
	@Autowired
	Imagerepository imageRepository;
	
	public Image saveImage(Image image){
		return imageRepository.save(image);
		
	}
	
	public Image findImgByID(Long id) {
        
	return imageRepository.findById(id).orElse(null);
	
    }
	

}
