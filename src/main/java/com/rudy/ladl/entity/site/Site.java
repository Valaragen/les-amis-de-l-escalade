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
    @Column(length = 5000)
    private String accessInfo;
    @Column(length = 5000)
    private String additionalInfos;

    @Column(precision=9, scale=6, nullable = false)
    private Double latitude;
    @Column(precision=9, scale=6, nullable = false)
    private Double longitude;

    @Column(precision=9, scale=6)
    private Double parkingLatitude;
    @Column(precision=9, scale=6)
    private Double parkingLongitude;

    @Column
    private Boolean kidsFriendly = false;
    @Column(length = 5000)
    private String kidsFriendlyInfo;

    @Column
    private Integer cragsNumber;
    @Column
    private Integer bottomRoutesAltitude;
    @Column
    private Integer maxRoutesHeight;
    @Column(length = 50)
    private String nearestVillage;
    @Column(length = 50)
    private String nearestBigCity;

    @ManyToOne
    private RoutesNumber routesNumber;
    @ManyToOne
    private RockType rockType;
    @ManyToOne
    private Grade minGrade;
    @ManyToOne
    private Grade maxGrade;
    @ManyToOne
    private Department department;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LevelGroup> levelGroups = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Orientation> orientations = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SiteType> siteTypes = new HashSet<>();

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
