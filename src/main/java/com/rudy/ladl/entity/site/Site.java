package com.rudy.ladl.entity.site;

import com.rudy.ladl.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Site extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Column(length = 30, nullable = false)
    private String township;
    @Column(length = 5000)
    private String description;
    @Column(length = 30)
    private String type;
    @Column(length = 5000)
    private String access_info;
    @Column(length = 20)
    private String orientation;

    @ManyToOne
    private Department department;


    @ManyToMany(mappedBy = "sites")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Area> areas = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SiteContribution> contributions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AddedTag> addedTags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments = new HashSet<>();
}
