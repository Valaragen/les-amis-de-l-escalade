package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
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
public class LevelGroup extends AbstractEntity {
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Grade minGrade;
    @ManyToOne
    private Grade maxGrade;

    @ManyToMany(mappedBy = "levelGroups")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Site> sites = new HashSet<>();

}
