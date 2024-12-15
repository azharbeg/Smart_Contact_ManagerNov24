package com.scm.forms;



@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
@ToString
public class ContactForm {
    
    @NotBlank(message = " name is required") // yha hum validate kar rhe hai form ki text field  ko
    private String name;

    @NotBlank(message = "Message is required")
    @Email(message = "Invalid email address [ expample@gmail.com ]")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private String description;

    private boolean favorite = false;

    private String websiteLink;

    private String linkedInLink;

    //annotation create krenge jo hmari file ko validate kreneg
    //size, resulution,type
    // but phle isme file aani bhi chahiye

    @ValidFile
    private MultipartFile contactImage;


}
