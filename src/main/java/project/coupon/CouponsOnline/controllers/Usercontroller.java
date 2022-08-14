package project.coupon.CouponsOnline.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import project.coupon.CouponsOnline.models.User;
import project.coupon.CouponsOnline.models.Usertype;
import project.coupon.CouponsOnline.services.Userservice;

@Controller
@RequestMapping("/User")
public class Usercontroller {

	@Autowired
	Userservice userService;
	/*
	 * @Autowired private CacheManager cacheManager; // autowire cache manager
	 */

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public RedirectView addUser(User user, RedirectAttributes redirectAttrs) {
		if (userService.emailExist(user.getEmail())) {

			redirectAttrs.addFlashAttribute("alreadyExistError", "Email Already exist");
			return new RedirectView("/signup", true);
		} else {

			userService.save(user);
			redirectAttrs.addFlashAttribute("alreadyExistError", null);

			return new RedirectView("/signin", true);
		}

	}

	@RequestMapping(value = "/loginModule", method = RequestMethod.POST)
	public RedirectView loginUser(User user, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		if (userService.credentialsMatch(user.getEmail(), user.getPassword(), user.getUserType())) {
			redirectAttrs.addFlashAttribute("wrongcredentials", null);
			request.getSession().setAttribute("loggedin", true);
			request.getSession().setAttribute("user", userService.findByEmail(user.getEmail()));

			if (user.getUserType().equals(Usertype.Admin)) {
				return new RedirectView("/signup", true);

			} else {

				return new RedirectView("/", true);

			}
			// request.getSession().setAttribute("",);
		} else {

			redirectAttrs.addFlashAttribute("wrongcredentials", "Email or Password Incorrect!");

			return new RedirectView("/signin", true);
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public RedirectView logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return new RedirectView("/", true);

	}

	

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public RedirectView editUser(User user, RedirectAttributes redirectAttrs, HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("old_pass") String oldPass, @RequestParam("newPass") String newPass) {
		User userObj = (User) request.getSession().getAttribute("user");

		if (userObj.getPassword().equals(oldPass)) {

			if ((!userObj.getEmail().equals(email)) && (userService.emailExist(email))) {

				redirectAttrs.addFlashAttribute("updateMessage", "Email Already exist");
			} else {
				userObj.setEmail(email);
				userObj.setName(name);
				if(!newPass.equals("")){
				userObj.setPassword(newPass);}
				System.out.println(userObj.toString());

				userService.updateUser(userObj);
				//System.out.println(userObj.toString());
				System.out.println(userService.findByEmail(user.getEmail()).toString());
				request.getSession().setAttribute("user", userService.findByEmail(user.getEmail()));
				redirectAttrs.addFlashAttribute("updateMessage", "Values Changed Successfully");

			}
		}

		else {
			redirectAttrs.addFlashAttribute("updateMessage", "Wrong old password!");

		}

		return new RedirectView("/profile", true);

		/*
		 * System.out.println(user.getName()+" : "+user.getEmail()+" : "+user.
		 * getPassword()+" : "+oldPass); return new RedirectView("/", true);
		 */

	}

}
