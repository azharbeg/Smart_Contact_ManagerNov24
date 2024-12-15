package com.scm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

import config.Autowired;

/*
 * isme user se related protected jitne bhi
 * url honge , unse related jitne bhi handler honge
 * vo sab is user controller ke ander honge
 *  
 */
@Controller
@RequestMapping("/user") // /user-> kyoki ye user se start hone wale url ko handle krega
public class UserController {

    // yha par hum user ke liye view bnayenge jo user ko dikhai de

    // userService use kr rhe hai
    @Autowired
    private UserService userService;

   

    // user dashboard page
    @RequestMapping(value="/dashboard", method= RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }

    // user profile page handler
    @RequestMapping(value="/profile", method= RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication){
        
        String name = Helper.getEmailOfLoggedInUser(authentication);
       
        Object username;
    // ager hume email id mil ja rhi hai toh,toh hum userService use krenge
        // and us email id ke coresponding  user ko fetch kar lenge
       User user = userService.getUserByEmail(username);

        System.out.println(name.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedinUser", user);


        return "user/profile";
    }


    // user add contact page


    //user view contact


    // user edit contact


    // user delete contact

    



}
