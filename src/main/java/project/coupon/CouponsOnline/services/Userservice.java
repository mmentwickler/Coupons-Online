package project.coupon.CouponsOnline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.coupon.CouponsOnline.models.User;
import project.coupon.CouponsOnline.models.Usertype;
import project.coupon.CouponsOnline.repositories.Userrepository;

@Service
public class Userservice {
	
	@Autowired
	Userrepository userRepository;
	
	public User save(User user) {
		
		if(emailExist(user.getEmail()))
		{
	    return null;
		}else
		{
		return userRepository.save(user);
		}
		
	}
	
	public User updateUser(User user){
		return userRepository.saveAndFlush(user);
	}
	
	
	
	   public boolean emailExist(String email) {
	        User user = userRepository.findByEmail(email);
	        if (user != null) {
	            return true;
	        }
	        return false;
	    }
	   
	   
	   public boolean credentialsMatch(String email,String password,Usertype userType) {
	        User user = userRepository.findByEmailAndPasswordAndUserType(email, password,userType);
	        if (user != null) {
	            return true;
	        }
	        return false;
	    }
	   
	   public User findByEmail(String email){
		   return userRepository.findByEmail(email);
	   }
	
	/*Retrieve data frokm database
	public List<User> findEmployee(){
		return userRepository.;
	}
	
	public Employee findOne(Long empid){
		return employeeRepository.findById(empid).orElse(null);
	//	return (Employee)employeeRepository.findById(empid);
		
	}
	
	public void del_Emp(Employee emp){
		employeeRepository.delete(emp);
	}
	*/
	
	

}
