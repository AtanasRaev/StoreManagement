package com.storemanagement.database.dto;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {
    @Size(min = 4, max = 15, message = "The password must be between 4 and 15 characters")
    private String password;
}
