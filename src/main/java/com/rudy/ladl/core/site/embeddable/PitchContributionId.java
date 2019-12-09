package com.rudy.ladl.core.site.embeddable;

import com.rudy.ladl.core.site.Pitch;
import com.rudy.ladl.core.site.PitchField;
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
public class PitchContributionId implements Serializable {

    @ManyToOne
    private PitchField field;

    @ManyToOne
    private Pitch pitch;

}
