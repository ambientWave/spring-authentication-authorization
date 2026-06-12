package com.ambientwave.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty; // jakarta.validation is the Bean Validation API (annotations like @NotNull, @NotEmpty). It's a separate library — it does not come with spring-boot-starter-web or JPA by default. The classpath simply doesn't have it, so the import can't be resolved. The Fix — Add the validation starter to pom.xml

@Entity
public class ResUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    @Column(unique = true) // important to avoid duplicate usernames and hence avoid
                           // "InternalAuthenticationServiceException: Query did not return a unique
                           // result: 2
                           // results were returned" - in the login page.
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Role is required")
    private String role;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
