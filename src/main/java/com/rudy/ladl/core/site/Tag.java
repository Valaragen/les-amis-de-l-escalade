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
public class Tag extends AbstractEntity {
    @Column(length = 40, nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "id.tag")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AddedTag> addedTags = new HashSet<>();
}
