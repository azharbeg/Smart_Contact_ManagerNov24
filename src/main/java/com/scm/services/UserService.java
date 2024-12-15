package com.scm.services;


 
import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

/*the service folder in a Spring application is where the "core business logic is
 implemented", acting as a bridge between the controller and repository layers. 
 It provides structure, maintainability, and a clear separation of concerns. */

public interface UserService {

     // all method use here, jo user ke liye honge
    
     //1. user ko save krna
     User saveUser(User user);

     Optional<User> getUserById(String id);
     // Optional-> ye hume btata hai ki user hai ya nhi then base on it hum operation perform kar skte hai
     Optional<User> updateUser(User user);
     void deleteUser(String id);
     boolean isUserExist(String userId);
     boolean isUserExistByEmail(String email);

     // if we want all users
     List<User> getAllUsers();

     com.scm.controller.User getUserByEmail(Object username);
     

     // if needed-> add more methods here related user service(logic)

}
