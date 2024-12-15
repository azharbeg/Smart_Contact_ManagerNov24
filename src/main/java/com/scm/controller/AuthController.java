package com.scm.controller;

import com.scm.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.repositeries.UserRepo;

import config.Autowired;
import jakarta.servlet.http.HttpSession;

//@Controller
//@RequestMapping{"/auth"}
public class AuthController {
    

    // token hume mil rha hai toh is token se hum user ko fetch krenge
    @Autowired
    private UserRepo userRepo;


    // varify email (jab hum varify link par click krenge toh ye wala method chalega)

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token, HttpSession session){
        
      User user =  userRepo.findByEmailToken(token).orElse(null);
       
        if(user != null){
            // user fetch hua hai : usi hisaab se procss krna hai

            if(user.getEmailToken().equals(token))
            {
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);
                
                return "success_page";
            }
            return "Error_page";
        }

      System.out.println("chal rha hai kya?");
        return "Error_page";
    }
}
