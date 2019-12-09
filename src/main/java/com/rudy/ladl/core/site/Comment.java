package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntityComposedId;
import com.rudy.ladl.core.site.embeddable.CommentId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Comment extends AbstractEntityComposedId {

    @EmbeddedId
    private CommentId id;

    @Column(length = 3000)
    private String description;
}
