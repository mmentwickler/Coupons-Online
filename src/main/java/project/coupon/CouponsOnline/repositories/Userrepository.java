package project.coupon.CouponsOnline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.coupon.CouponsOnline.models.User;
import project.coupon.CouponsOnline.models.Usertype;

public interface Userrepository extends JpaRepository<User,Long> {

	User findByEmail(String email);
	
	User findByEmailAndPasswordAndUserType(String email,String password,Usertype userType);

	//User save(User u);

}
