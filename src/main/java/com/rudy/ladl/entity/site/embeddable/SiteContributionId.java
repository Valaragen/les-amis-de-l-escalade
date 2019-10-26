package com.rudy.ladl.entity.site.embeddable;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.site.SiteField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SiteContributionId implements Serializable {

    @ManyToOne
    private SiteField field;

    @ManyToOne
    private Site site;

}
