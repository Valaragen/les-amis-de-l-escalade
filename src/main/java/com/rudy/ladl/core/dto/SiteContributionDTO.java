package com.rudy.ladl.core.dto;

import com.rudy.ladl.core.site.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class SiteContributionDTO {

    private Boolean kidsFriendly;
    private Grade minGrade;
    private Grade maxGrade;
    private RockType rockType;
    private Set<Orientation> orientations;
    private Set<SiteType> siteTypes;
    private Set<LevelGroup> levelGroups;
    private RoutesNumber routesNumber;
    private Integer maxRoutesHeight;
    private Integer cragsNumber;
    private String description;
    private String kidsFriendlyInfo;
    private String additionalInfos;
    private String accessInfo;
    private Double parkingLatitude;
    private Double parkingLongitude;

    public Site mergeInSite(Site site) {
        if (kidsFriendly != null) {
            site.setKidsFriendly(getKidsFriendly());
        }
        if (minGrade != null) {
            site.setMinGrade(getMinGrade());
        }
        if (maxGrade != null) {
            site.setMaxGrade(getMaxGrade());
        }
        if (rockType != null) {
            site.setRockType(getRockType());
        }
        if (orientations != null && !orientations.isEmpty()) {
            site.setOrientations(getOrientations());
        }
        if (siteTypes != null && !siteTypes.isEmpty()) {
            site.setSiteTypes(getSiteTypes());
        }
        if (levelGroups != null && !levelGroups.isEmpty()) {
            site.setLevelGroups(getLevelGroups());
        }
        if (routesNumber != null) {
            site.setRoutesNumber(getRoutesNumber());
        }
        if (maxRoutesHeight != null) {
            site.setMaxRoutesHeight(getMaxRoutesHeight());
        }
        if (cragsNumber != null) {
            site.setCragsNumber(getCragsNumber());
        }
        if (description != null && !description.equals("")) {
            site.setDescription(getDescription());
        }
        if (kidsFriendlyInfo != null && !kidsFriendlyInfo.equals("")) {
            site.setKidsFriendlyInfo(getKidsFriendlyInfo());
        }
        if (additionalInfos != null && !additionalInfos.equals("")) {
            site.setAdditionalInfos(getAdditionalInfos());
        }
        if (accessInfo != null && !accessInfo.equals("")) {
            site.setAccessInfo(getAccessInfo());
        }
        if (parkingLatitude != null) {
            site.setParkingLatitude(getParkingLatitude());
        }
        if (parkingLongitude != null) {
            site.setParkingLongitude(getParkingLongitude());
        }
        return site;
    }

}
