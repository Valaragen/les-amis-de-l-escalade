package com.rudy.ladl.entity.site;

import com.rudy.ladl.entity.AbstractEntityComposedId;
import com.rudy.ladl.entity.site.embeddable.SiteContributionId;
import com.rudy.ladl.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SiteContribution extends AbstractEntityComposedId {

    @EmbeddedId
    private SiteContributionId id;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @ManyToOne
    private User user;

}
