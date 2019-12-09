package com.rudy.ladl.core.site.embeddable;

import com.rudy.ladl.core.site.Route;
import com.rudy.ladl.core.site.RouteField;
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
public class RouteContributionId implements Serializable {

    @ManyToOne
    private RouteField field;

    @ManyToOne
    private Route route;

}
