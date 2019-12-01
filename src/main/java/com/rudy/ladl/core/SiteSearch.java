package com.rudy.ladl.core;

import lombok.Data;

import java.util.Set;

@Data
public class SiteSearch {
    private String name;
    private Boolean kidsFriendly;
    private Long departmentId;
    private Long GradeId;
    private Long siteTypeId;
    private Long levelGroupId;
    private Long routesNumberId;
    private Integer minRouteHeight;

}
