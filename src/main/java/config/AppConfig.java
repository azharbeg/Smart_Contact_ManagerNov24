



@Configuration
public class AppConfig {
    
     @Value("${cloudinary.cloud.name}")      // properties file se value yha lane ke liye use kre rhe hai
    private String cloudName;

    @Value("${cloudinary.api.key}")       
    private String apiKey;

    @Value("${cloudinary.api.secret}")       
    private String apiSecret;


    @Bean
    public Cloudinary cloudinary(){

        return new Cloudinary(

            ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key",apikey,
                "api_secret", apiSecret)
            
        );


        
    };
}
