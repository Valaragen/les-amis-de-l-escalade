package com.rudy.ladl.entity.site.embeddable;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CommentId implements Serializable {

    @ManyToOne
    private Site site;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

}
