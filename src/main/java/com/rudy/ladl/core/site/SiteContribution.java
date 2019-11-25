package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntityComposedId;
import com.rudy.ladl.core.site.embeddable.SiteContributionId;
import com.rudy.ladl.core.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

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
