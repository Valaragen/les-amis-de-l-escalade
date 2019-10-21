package com.rudy.ladl.core.user;

import com.rudy.ladl.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "_user")
public class User extends AbstractEntity {

    @NotBlank
    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(length = 64, nullable = false)
    private String password;

    @NotBlank
    @Column(length = 30, nullable = false)
    private String firstName;

    @NotBlank
    @Column(length = 30, nullable = false)
    private String lastName;

    @Email
    @NotBlank
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

}
