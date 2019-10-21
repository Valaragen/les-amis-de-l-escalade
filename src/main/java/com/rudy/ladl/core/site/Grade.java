package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Grade extends AbstractEntity {
    @Column(length = 10, nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "grade")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Route> route = new HashSet<>();

    @OneToMany(mappedBy = "grade")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pitch> pitch = new HashSet<>();

}
