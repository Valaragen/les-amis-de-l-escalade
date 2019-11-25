package com.rudy.ladl.core.site.embeddable;

import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.Tag;
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
public class AddedTagId implements Serializable {

    @ManyToOne
    private Site site;

    @ManyToOne
    private Tag tag;
}
