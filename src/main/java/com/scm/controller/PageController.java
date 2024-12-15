package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

 
@Controller /*This class typically handles web requests, processes user input, interacts with the service layer, and returns a response (often in the form of views like HTML pages or JSON). */
// it's responsible for handling HTTP requests from the client
public class PageController {

    // @controller likhne ke baad ab hum 
	// is function ke ander aisa method likh skte hai
	// jo HTTP request ho handle krega

	// Service ko inject kar rhe hai 
	@Autowired
	private UserService userService;

    // below method execute hoga jab hum koi url fire krenge ya
	//  request bejhenge
    @GetMapping("/home") // jaise hi hum /home fire krenge browser par below method chal jayega
    public String home(Model model)/// Model is a container used to pass data from the controller to the view...,  // Ager hume koi data html(home.html) ko bejna hai toh
    {
        // sending data to view
         model.addAttribute("name" , "Azhar Beg");
		model.addAttribute("course","Spring Boot with Thymeleaf");
		model.addAttribute("githubRepo", "https://github.com/azharbeg/pushrepo");
        return "home";
    }

    // about page handler
	@GetMapping("/about")
	public String aboutPage(){
		System.out.println("About page loading");
		return "about";
	} 

    // service page handler
	@GetMapping("/services")
	public String servicesPage(){
		System.out.println("Services page loading");
		return "services";
	}

	// contact page handler
	@GetMapping("/contact")
	public String contactPage(){
		System.out.println("contact page loading");
		return "contact";
	}

	// login page handler
	@GetMapping("/login")
	public String loginPage(){
		System.out.println("login page loading");
		return "login";
	}

	// register page handler
	@GetMapping("/register")
	public String registerPage(Model model){
		 
		// ek blank object yha se bej rhe hai register page par
		UserForm userform = new UserForm();
		// Default data bhi daal skte hai	 
		model.addAttribute( "userform",userform); // ab is userform ko hum apne page (register.html) par access kar skte hai(but now it's contain blank data)

		return "register";
	}

	// processing register
	@RequestMapping(value = "/do-register",method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rbindingresult, HttpSession session){
		System.out.println("Processing registration");

		// fetch from data
		// UserForm
		//validate form data
		if(rbindingresult.hasErrors()){
			return "register";
		}

		// save to database
		   //userservice -> 
		       /* A UserService is often annotated with @Service and it's  a service layer class that 
			   contains business logic related to user management, such as creating, updating,
			    retrieving, and deleting users from the system  and contains methods to manage user data.
			
				*/

				// database mai data use krte hai userService use krke
				User user= new  User();
				user.setName(userForm.getName());
				user.setEmail(userForm.getEmail());
				user.setPassword(userForm.getPassword());
				user.setAbout(userForm.getAbout());
			    user.setPhoneNumber(userForm.getPhoneNumber());
				
			 
				User savedUser =  userService.saveUser(user);


		// message = "Registration Successful"

		// add the message
		Message  message = Message.builder().content("Registration Successful").type(MessageType.green).build();

		session.setAttribute("message", message);


		// redirect to login page

		 
		return "redirect:/register";
	}	
}
