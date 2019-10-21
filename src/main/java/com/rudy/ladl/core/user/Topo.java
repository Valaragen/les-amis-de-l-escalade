package com.rudy.ladl.core.user;

import com.rudy.ladl.core.AbstractEntity;
import com.rudy.ladl.core.site.Department;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Topo extends AbstractEntity {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean status;

    @Column(length = 500)
    private String desc;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Department department;


    @ManyToMany
    @JoinTable(name = "topo_booking",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "topo_id", referencedColumnName = "id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> bookingAskers = new HashSet<>();
}
