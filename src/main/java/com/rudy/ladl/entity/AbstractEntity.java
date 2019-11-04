package com.rudy.ladl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Version
    protected Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

}
