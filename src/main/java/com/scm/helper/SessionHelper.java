package com.scm.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import jakarta.servlet.http.HttpSession;

// ek baar message display krne ke baad
// dobara referesh krne par us message ko show nhi hona dega
@Component
public class SessionHelper {
    
    public static void removemessage(){

        try{
                System.out.println("removing message from session");
                // firstly we get object of message
            HttpSession session = ((ServletRedquestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message"); 
        }
        catch(Exception e){
            System.out.println("eror in sessionHelper:"+e);
            e.printStackTrace();
        }
    }
}
