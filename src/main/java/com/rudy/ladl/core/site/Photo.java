package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import com.rudy.ladl.core.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Photo extends AbstractEntity {

    @Column(unique = true)
    private String path;

    @ManyToOne
    private User user;


    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Site> sites = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Area> areas = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Route> routes = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pitch> pitches = new HashSet<>();

}
