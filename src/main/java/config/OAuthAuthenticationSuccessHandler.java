package config;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.repositeries.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Component
public class OAuthAuthenticationSuccessHandler<Logger> implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
   
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, Authentication authentication,
         HttpServletResponse response) throws IOException, ServletException {

            @Autowired UserRepo userRepo;

            logger.info("onAuthenticationSuccess");
            response.sendRedirect("/home");


            // first identify the provider 
            var oauth2AuthenticationToken= (OAuth2AuthenticationToken) authentication;
              
            // isse hume pta chal jayega ki hum kon sa provider user kr rhe hai google ya github
            String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
            
            logger.info(authorizedClientRegistrationId);

            var oauthUer=(DefaultOAuth2User)authentication.getPrincipal();
           
            oauthUser.getAttributes().forEach((key,value)->{
                logger.info(key + " : " + value);
            });

            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setRoleList(List.of(AppConstants.ROLE_USER));
            user.setEmailVarified(true);
            user.setEnabled(true);
           
            if(authorizedClientRegistrationId.equal){

                // google
              // attribute of google
              user.setEmail(oauthUser.getAttribute("emaail").toString());
              user.setProfilePic(oauthUser.getAttribute("picture").toString());
              user.setName(oauthUser.getAttribute("name").toString());
              user.setProviderUserId(oauthUser.getName());
              user.setProvider(Providers.GOOGLE);
              user.setAbout("This account is created");
            }
            

            else if(authorizedClientRegistrationId.equalsIgnoreCase("linkedin")){

                // github
              // attribute of github
              String email = oauthUser.getAttribute("email")!= null ? oauthUser.getAttrubute("email").toString()
                 : oauthUser.getAttribute("login").toString() + "@gmail.com";

                 String picture = oauthUser.getAttribute("avatar_url").toString();
                 String name=oauthUser.getAttribute("login").toString();
                 String providerUserId = oauthUser.getName();

                 user.setEmail(email);
                 user.setProfilePic(picture);
                 user.setName(name);
                 user.setProviderUserId(providerUserId);
                 user.setProvider(Providers.GITHUB);
                 user.setAbout("this account created by github");
            }

            else{ // ye btayega ki known provider hai
                logger.info("OAuthAuthenticationSuccessHandler: Unknown provider");

            }
              
            // save the user 
            User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
            if(user2==null){
                userRepo.save(user);
            }
            

             
            /* 
            DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
            
            logger.info(user.getName());
             
            user.getAttributs().forEach((key,value)->{

                logger.info("{} : {}",key,value);
            }); 

            logger.info(user.getAuthorities().toString());
            // data database  save:

            String email = user.getAttribute("email").toString();
            String name = user.getAttribute("name").toString();
            String picture = user.getAttribute("picture").toString();
 
            // create user and save in database
            User user1 = new User();
            user1.setEmail(email);
            user1.setName(name);
            user1.setProfilePic(picture);
            user1.setPassword("password");
            user1.setUserId(UUID.randomUUID().toString());
            user1.setEnabled(true);
            user1.setAccountNonExpired(true);

            user1.setEmailVarified(true);
            user1.setProviderUserId(user.getName());
            user1.setRoleList(List.of(AppConstants.ROLE_USER));
            user1.setAbout("This account is created using google..");


            /// user ko fetch krenge
            // if user found then it will not save to database otherwise kuch nhi hoga, if not found the save into database 
            User user2 = userRepo.findUserByEmail(email).orElse(null);
            if(user2==null){
                userRepo.save(user1);
                logger.info("User saved" + email);
            }
            

            */
            
            DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
         }
    

}
