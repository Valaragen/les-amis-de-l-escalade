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
public class Area extends AbstractEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    private Site site;


    @ManyToMany(mappedBy = "areas")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.area")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AreaContribution> contributions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "area")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Route> routes = new HashSet<>();

}
