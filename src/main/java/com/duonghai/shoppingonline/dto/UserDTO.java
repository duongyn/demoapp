package com.duonghai.shoppingonline.dto;

import com.duonghai.shoppingonline.model.Provider;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class UserDTO {
    Integer id;
    @NotBlank(message = "Name is mandatory")
    String username;
    @NotBlank(message = "Password is mandatory")
    String password;
    String firstName;
    String lastName;
    @NotBlank(message = "Email is mandatory")
    String emailAddress;
    Provider provider;
    List<String> authorities;

    @Override
    public String toString() {
        return "User with id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", provider=" + provider + '\'' +
                ", roles=" + authorities;
    }

}
