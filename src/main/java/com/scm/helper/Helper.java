package com.scm.helper;

public class Helper {
    
    public static String getEmailOfLoggedInUser(Authentication authentication){

        AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();

        // ager email is passqord se login kia hai toh hum email kaise nikalenge
        if(authentication instanceof OAuth2AuthenticationToken){

            var aOAuthenticationToken = (oauth2AuthenticationToken)authentication;
            var clientId = aOAuthenticationToken.getAuthorizedClientRegistrationId();

            String username="";

            if(clientId.equalsIgnoreCase("google"))
            {
                        // sign with google kia hai to kaise nikalenge

                        System.err.println("getting email form google");
                       username = oauth2User.getAttribute("email").toString();
            }
            else if(clientId.equalsIgnoreCase("github")){
                
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString
                 : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
        
            return username;
        // sign with github kia hai to kaise nikalenge
        }else{
            System.out.println("Getting data from local database");
            return authentication.getName();        }
        
    }
}
