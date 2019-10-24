package com.rudy.ladl.entity.site;

import com.rudy.ladl.entity.AbstractEntityComposedId;
import com.rudy.ladl.entity.site.embeddable.AreaContributionId;
import com.rudy.ladl.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class AreaContribution extends AbstractEntityComposedId {

    @EmbeddedId
    private AreaContributionId id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @ManyToOne
    private User user;
}
