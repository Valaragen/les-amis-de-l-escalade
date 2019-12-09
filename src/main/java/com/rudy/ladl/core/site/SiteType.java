package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SiteType extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "siteTypes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Site> sites = new HashSet<>();

}
