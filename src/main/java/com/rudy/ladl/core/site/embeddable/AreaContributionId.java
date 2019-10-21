package com.rudy.ladl.core.site.embeddable;

import com.rudy.ladl.core.site.Area;
import com.rudy.ladl.core.site.AreaField;
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
public class AreaContributionId implements Serializable {

    @ManyToOne
    private AreaField field;

    @ManyToOne
    private Area area;

}
