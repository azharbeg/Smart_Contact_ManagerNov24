package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    // iske ander hum vo field lenge
    // jo hmare form ke ander bnaye gye huye hai
    // unka data hum yha par receive krenge

    @NotBlank(message = "Username is required") // yha hum validate kar rhe hai every field ko
    @Size(min = 3, message = "Min 3 Charaters is required")
    private String name;

    @Email(message = "Invalid Email address")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "about is required")
    private String about;
    @Size(min = 8, max = 12, message = " Invalid phone number")
    private String phoneNumber;

    public String getProfilePic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProfilePic'");
    }
}
