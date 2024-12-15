package com.scm.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String>{


    // find the contact by user (below method called-> custom finder method)
    Page<Contact> findByUser(User user, Pageable pageable);


    // this is called custom query method both are same
    @Query("SELECT c from Contact c where c.user.id = : userID")
    List<Contact>findByUserId(@Param("userId")String userId);

    // search method

    List<Contact> findByNameContaining(String nameKeyword,Pageable pageable,User user);

    List<Contact> findByEmailContaining(String emailKeyword,Pageable pageable,User user);

    List<Contact> findByPhoneContaining(String phoneKeyword,Pageable pageable,User user);

}
