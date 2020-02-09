package org.com.sda.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotNull @NotEmpty
    private String firstName;
    @NotNull @NotEmpty
    private String lastName;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize kkk");
    }
}
