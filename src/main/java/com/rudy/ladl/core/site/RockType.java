package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class RockType extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String name;
}
