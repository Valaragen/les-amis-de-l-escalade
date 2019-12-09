package com.rudy.ladl.core.dto;

import com.rudy.ladl.core.user.Topo;
import com.rudy.ladl.core.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopoReservationDTO {
    private User user;
    private Topo topo;

}
