package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PitchField extends AbstractEntity {

    @Column(length = 30, nullable = false)
    private String name;

}
