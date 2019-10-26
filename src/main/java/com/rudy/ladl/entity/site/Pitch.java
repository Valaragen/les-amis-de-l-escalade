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
public class Pitch extends AbstractEntity {

    @ManyToOne
    private Grade grade;

    private int height;

    @Column(length = 300)
    private String remark;

    @ManyToOne
    private Route route;

    @ManyToMany(mappedBy = "pitches")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.pitch")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PitchContribution> contributions = new HashSet<>();

}
