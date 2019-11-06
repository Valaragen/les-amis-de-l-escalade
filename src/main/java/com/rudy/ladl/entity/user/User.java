package com.rudy.ladl.entity.user;

import com.rudy.ladl.entity.AbstractEntity;
import com.rudy.ladl.util.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "_user")
public class User extends AbstractEntity {

    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = Constant.ERROR_MSG_USERNAME_INVALID_CHAR)
    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @Size(min = 5, max = 40)
    @Pattern(regexp = "^(?=.*[A-Z])[A-Za-z\\d]+$", message = Constant.ERROR_MSG_PASSWORD_NOT_COMPLETE)
    @Transient
    private String password;

    @Size(min = 60, max = 64)
    @Column(length = 64, nullable = false)
    private String encryptedPassword;

    @Size(min = 2, max = 30)
    @Column(length = 30, nullable = false)
    private String firstName;

    @Size(min = 2, max = 30)
    @Column(length = 30, nullable = false)
    private String lastName;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z]{2,}[.]{1}[A-Za-z]{2,}$", message = "L''email n''est pas valide")
    @Size(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();


    @ManyToMany(mappedBy = "bookingAskers")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Topo> toposAskedForBooking = new HashSet<>();

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    public void setEmail(String email){
        this.email = email.toLowerCase();
    }

    public String getRolesNamesAsFormattedString(){
        StringBuilder roleFormatted = new StringBuilder();
        for (Role role: roles) {
            roleFormatted.append(role.getName().replaceAll("ROLE_", "")).append(", ");
        }
        roleFormatted.setLength(roleFormatted.length()-2);
        return roleFormatted.toString();
    }

    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
            role.addUser(this);
        }
    }

}
