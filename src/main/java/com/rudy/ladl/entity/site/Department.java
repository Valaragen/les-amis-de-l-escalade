package com.rudy.ladl.entity.site;

import com.rudy.ladl.entity.AbstractEntity;
import com.rudy.ladl.entity.user.Topo;
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
public class Department extends AbstractEntity {

    @Column(length = 10, nullable = false, unique = true)
    private String departmentNumber;

    @Column(length = 50, nullable = false)
    private String name;


    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Topo> topos = new HashSet<>();

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Site> sites = new HashSet<>();
}
