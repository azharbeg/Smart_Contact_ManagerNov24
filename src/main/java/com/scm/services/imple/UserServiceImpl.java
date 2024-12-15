 /* UserService.java ke method ki implementation hum 
 * yha likhenge
 */

 package com.scm.services.imple;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositeries.UserRepo;
import com.scm.services.EmailService;
import com.scm.services.UserService;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService {


    // we need repository here(property injection use here)
    @Autowired
    private UserRepo userRepo; // databse mai data ko save krega

    // to log message for debugging
    private Logger  logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;
    
    @Override
    public User saveUser(User user) {
       
        // user id have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        //password encode
        // user.setPassword(userId);

        // user save ho jayega yha
        User savedUser = userRepo.save(user);

        // yha ek token generate krenge
        String emailToken = UUID.randomUUID().toString();

        // yha hum link fetch krenge
        String emailLink = Helper.getLinkForEmailVerification(emailToken);
        // ab hume email ko bejna hai uper wali link to hume emailService chahiye

        emailService.sendEmail(savedUser.getEmail(), "Varify email : email contact manager", emailLink);
    } 

    @Override
    public Optional<User> getUserById(String id) {
       
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        
        //user2 ke ander vo information hai jo database se aai hai
       User user2 = userRepo.findById(user.getUserId().orElseThrow(()-> new ResourceNotFoundException("user not found")));
       // update krenge user2 ko from user
       user2.setName(user.getName());
       user2.setEmail(user.getEmail());
       user2.setPassword(user.getPassword());
       user2.setAbout(user.getAbout());
       user2.setPhoneNumber(user.getPhoneNumber());
       user2.setProfilePic(user.getProfilePic());
       user2.setEnabled(user.isEnabled());
       user2.setEmailVarified(user.isEmailVarified());
       user2.setPhoneVarified(user.isPhoneVarified());
       user2.setProvider(user.getProvider());
       user2.setProviderId(user.getProviderId());

       // save the user in database
      User save = userRepo.save(user2);   
      return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        
        // fetch the user first
        User user2 = userRepo.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("user not found"));
        userRepo.delete(user2);
        
    }

    @Override
    public boolean isUserExist(String userId) {
        
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        
        User user2 = userRepo.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        
        return userRepo.findAll();
    }


}
