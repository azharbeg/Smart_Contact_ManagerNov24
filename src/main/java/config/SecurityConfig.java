package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Injecting custom user details service
    private final SecurityCustomUserDetailService userDetailService;
    private final OAuthAuthenticationSuccessHandler handler;

    // Constructor-based injection for required dependencies
     public SecurityConfig(SecurityCustomUserDetailService userDetailService, OAuthAuthenticationSuccessHandler handler) {
        this.userDetailService = userDetailService;
        this.handler = handler;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // Set custom user details service
        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // Set password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

   // @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configure security filter chain
        httpSecurity
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/home", "/register", "/service").permitAll() // Public pages
                            .requestMatchers("/user/**").authenticated() // Protected pages
                            .anyRequest().permitAll(); // Allow all other requests
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/login") // Custom login page
                            .loginProcessingUrl("/authenticate") // Login processing URL
                            .defaultSuccessUrl("/user/dashboard", true) // Redirect after successful login
                            .usernameParameter("email") // Email as username parameter
                            .passwordParameter("password"); // Password parameter
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/logout") // Logout URL
                            .logoutSuccessUrl("/login?logout=true"); // Redirect after logout
                })
                .oauth2Login(oauth -> {
                    oauth
                            .loginPage("/login") // OAuth2 login page
                            .successHandler(handler); // Custom success handler
                })
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (not recommended for production)

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
























//package config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//// isme hum vo sari cheez configure kar skte hai
//// aisa code config kr skte hai ki jo database wala
//// username or password hai  co lele khud se
//// configure hum as a bean krenge
//
///*
// * NOTE:-   Spring security user details service ko use krta hai
// *      kaam karne ke liye means jab bhi hum login krte hai vo user ke
// *     details ko fetch krta hai uske liye ye UserDetailsService ko use krta hai.
// *      us service ke pass ek method hota hai LoadUserByUsername isko call krega
// *      user ko load krane ke liye then jo loaded user hai and hamara user hai
// *      unke password ko match krega ager password match hota hai toh ye login kra deta hai
// *
// *
// *
// */
//
//@Configuration
//public class SecurityConfig {
//
//    // user create and login using java code with in memory service
//    //  @Bean
//    //  public UserDetailsService userDetailsService(){
//
//    //     // store userdetails here jo user data aa rha hai
//    //     UserDetails user1 = User
//    //      .withDefaultPasswordEncoder()
//    //     .Username("admin123").password("admin123")
//    //     .build();
//
//    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//    //     return inMemoryUserDetailsManager;
//    //  }
//
//    //@Autowired
//    private SecurityCustomUserDetailService userDetailService;
//
//   // @Autowired
//    private OAuthAuthenticationSuccessHandler handler;
//    //////////////////////////////////////////////////////////////////
//    //...yha hai user login credentials ko apne database ke user se match kra rhe hai
//        // configuration of authentication provider for spring security
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//
//        // is DaoAuthenticationProvider ke pass  vo sare method hai
//        // jinki help se hum register kar skte hai apni service
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//        // user detail service ka object lana hai or yha pass krna hai
//        daoAuthenticationProvider.setUserDetailsService((UserDetailsService) userDetailService);
//
//        //password encoder ka object lana hai or yha pass krna hai
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return daoAuthenticationProvider;
//    }
//
//    /*
//     * isse hum bta skte hai ki kon se pages hume configure krne hai, kon sa routes hoga,
//     * kon sa user kon se pages ko access kar payega
//     * and form login chahiye ya AuthO login chahiye
//     */
//
//
//    @Bean
//    public SecurityFilterChain securtiyFilterChain(HttpSecurity httpSecurity){
//
//        //configuration kr rhe hai pages ko
//        // yha par home,register,services page public ho jayega baki sab protected honge
//        httpSecurity.authorizedHttpRequest(authorize->{
//           // authorize.requestMatchers("/home","/register","/service").permitAll();// this is actually authorization manage request registry
//
//            // Ager hume sirf kuch url protect krne hai toh
//            autorize.requestMatchers("/user/**").authenticated(); // ager url 'user' se start hone wale sare url se match ho rha hai toh unko authenticate kar denge
//            // baki any request ko permit kar denge
//            authorize.anyRequest().permitAll();
//        });
//
//        // we need form login
//        // ager hume kuch bhi change krana hua to hum yaha aayenge form login se related
//        httpSecurity.formLogin(formLogin->{
//
//            //
//            formLogin.loginPage("/login")
//            .loginProcessingUrl("/authenticate")
//            .successForwardUrl("/user/dashboard")
//            .usernameParameter("email")
//            .passwordParameter("password");
//        });
//
//        // isse hum logout kar skte hai
//       httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        httpSecurity.logout(LogoutForm->{
//            logoutForm.logoutUrl("/logout");// ab koi banda logout hoga vo is url se hoga
//            logoutForm.logoutSuccessUrl("/login?logout=true");
//        });
//        return httpSecurity.build();
//    }
//
//    // oauth configuration
//    httpSecurity.oauth2Login(oauth -> {
//        Object oauth;
//        ((Object) oauth).loginPage("/login");
//        oauth.successHandler(handler);
//    });
//
//    private Customizer<FormLoginConfigurer<HttpSecurity>> withDefaults() {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'withDefaults'");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
//