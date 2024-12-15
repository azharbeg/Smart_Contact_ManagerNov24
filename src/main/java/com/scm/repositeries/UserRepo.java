package com.scm.repositeries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

/*   the repositories folder typically contains classes and interfaces that manage 
data access and communication with the database. These repositories are part of 
the data access layer (DAO layer) and are used to abstract the complexity of CRUD 
(Create, Read, Update, Delete) operations and queries. 
        the repositories folder serves as the core of the data access layer,
         managing how your application interacts with the database while 
         ensuring separation of concerns, maintainability, and efficiency.
*/

// repository database se intration krne ke liye hoti hai that means inke pass vo sare method honge
//  jo database se intract krenge  (ye method aayenge JpaRepository se <kis entity ke saath kaam kr rhe ho, type>)
@Repository
public interface UserRepo extends JpaRepository<User,String> {

    /*extra method do relatedoperations
     * custom query methods
     * custom finder methods
     */

    User save = null;

    // we can write extra methods for db related operations
    // like-> custom query mehtods, custom finder methods

    Optional<User> findByEmail(String email);
    // findByEmail ki query likne ki jrurt nhi hai iski implementation spring jpa khud se
    // likh dega or iska ek pattern le lega, 

      Object save(
              User user1
        );

    //ScopedValue<Object> findByEmailToken(String token);
    Optional<User> findByEmailToken(String token);



    // findByEmail() inko custom finder method bolte hai
    // similary findByUser(), findByPassword() etc..
} 
