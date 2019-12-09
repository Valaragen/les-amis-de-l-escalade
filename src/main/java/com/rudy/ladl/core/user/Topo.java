package com.rudy.ladl.core.user;

import com.rudy.ladl.core.AbstractEntity;
import com.rudy.ladl.core.site.Department;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
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
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date parutionDate;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Department department;


    @ManyToMany
    @OrderBy
    @JoinTable(name = "topo_booking",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "topo_id", referencedColumnName = "id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> bookingAskers = new HashSet<>();

    public String getSearchName() {
        return name.replace(" ", "_");
    }
}
