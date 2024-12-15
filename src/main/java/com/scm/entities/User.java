
//iska data hume database mai save krna hai
// means iski 1 table hmare database ke ander bnai jayegi

package com.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Providers;
import org.hibernate.engine.internal.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
@Builder
public class User implements UserDetails{

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;

    private boolean enabled = false;
    private boolean emailVarified = false;
    private boolean phoneVarified = false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;

    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    // for this project;
    //email id jo hai vohi hamara username hai
    @Override
    public String getUsername(){
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.enabled;
    }

   
    // ager hume below method ko dynamic bnana hai toh
    private List<String> roleList= new ArrayList<>();
  

    // ye method tab kaam ayega jab hum authority ki baat krenge
    // means ki user ke pass kon sa role hai
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Collections.emptyList();
    }

    public Object getEmailToken() {
        return null;
    }

    public void setEmailVerified(boolean b) {
    }
}





// package com.newscm.entities;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
//  import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;


// @Entity
// @Table(name = "users") //change the table name if we want
// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
//  public class User {

//     @Id
//     private String userId;
//     @Column(name = "user_name",nullable = false)
//     private String name;
//     @Column(unique = true, nullable = false)
//     private String email;
//     private String password;
//     @Column(length = 10000)
//     private String about;
//     @Column(length = 10000)
//     private String profilePic;
//     private String phoneNumber;

//     private boolean enabled=false;
//     private boolean emailVerified = false;
//     private boolean phoneVerified = false;


//     // which option use user for the signup
//     // self, google, facebook, twitter, linkedin, github
//     @Enumerated(EnumType.STRING)
//      private Providers provider = Providers.SELF;    // USE enum here

//     private String providerId;

//     //add more field if needed

//     // mapping here 1(user) to many(contact)
//      @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
//      private List<Contact> contacts = new ArrayList<>();




    
    

// }

