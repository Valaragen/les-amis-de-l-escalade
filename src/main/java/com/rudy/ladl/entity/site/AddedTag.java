package com.rudy.ladl.entity.site;

import com.rudy.ladl.entity.AbstractEntityComposedId;
import com.rudy.ladl.entity.site.embeddable.AddedTagId;
import com.rudy.ladl.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class AddedTag extends AbstractEntityComposedId {

    @EmbeddedId
    private AddedTagId id;

    @ManyToOne
    private User user;

}
