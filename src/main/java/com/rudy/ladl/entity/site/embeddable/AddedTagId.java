package com.rudy.ladl.entity.site.embeddable;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.site.Tag;
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
