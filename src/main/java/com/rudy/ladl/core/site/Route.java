package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "area_id"})})
public class Route extends AbstractEntity {

    @Column(nullable = false)
    private int number;

    @ManyToOne
    private Grade grade;

    private int height;

    @Column(length = 300)
    private String remark;

    @Column(length = 50)
    private String name;

    @ManyToOne
    private Area area;


    @ManyToMany(mappedBy = "routes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "route")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pitch> pitches = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.route")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RouteContribution> contributions = new HashSet<>();

}
