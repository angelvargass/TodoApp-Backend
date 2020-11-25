package vargas.angel.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull(message = "Email cannot be null")
    @Email(message = "Enter a valid email")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Password cannot be nul")
    @Size(min = 8, message = "Password must be 8 characters long")
    @JsonProperty("password")
    private String password;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, message = "Last name must be at least 3 characters long")
    @JsonProperty("lastName")
    private String lastName;

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
