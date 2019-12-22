package com.rudy.ladl.core.site;

import com.rudy.ladl.core.AbstractEntity;
import com.rudy.ladl.core.dto.SiteContributionDTO;
import com.rudy.ladl.core.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Site extends AbstractEntity {
    public enum AlterableFieldEnum {
        NAME("name"),
        TOWNSHIP("township"),
        DESCRIPTION("description"),
        ACCESSINFO("accessInfo"),
        ADDITIONALINFOS("additionalInfos"),
        LATITUDE("latitude"),
        LONGITUDE("longitude"),
        PARKINGLATITUDE("parkingLatitude"),
        PARKINGLONGITUDE("parkingLongitude"),
        KIDSFRIENDLY("kidsFriendly"),
        KIDSFRIENDLYINFO("kidsFriendlyInfo"),
        CRAGSNUMBER("cragsNumber"),
        MAXROUTESHEIGHT("maxRoutesHeight"),
        ROUTESNUMBER("routesNumber"),
        ROCKTYPE("rockType"),
        MINGRADE("minGrade"),
        MAXGRADE("maxGrade"),
        DEPARTMENT("department"),
        LEVELGROUPS("levelGroups"),
        ORIENTATIONS("orientations"),
        SITETYPES("siteTypes");

        private String name;

        AlterableFieldEnum(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    //TODO name can't have underscores regex, care with DTO
    @Size(min = 3, max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Size(min = 3, max = 30)
    @Column(length = 30, nullable = false)
    private String township;
    @Column(length = 5000)
    private String description;
    @Column(length = 5000)
    private String accessInfo;
    @Column(length = 5000)
    private String additionalInfos;

    @NotNull
    @Column(precision = 9, scale = 6, nullable = false)
    private Double latitude;
    @NotNull
    @Column(precision = 9, scale = 6, nullable = false)
    private Double longitude;

    @Column(precision = 9, scale = 6)
    private Double parkingLatitude;
    @Column(precision = 9, scale = 6)
    private Double parkingLongitude;

    @Column
    private Boolean kidsFriendly;
    @Column(length = 5000)
    private String kidsFriendlyInfo;

    @Column
    private Integer cragsNumber;
    @Column
    private Integer maxRoutesHeight;
    @Column(nullable = false)
    private Boolean isAcceptContribution;

    @ManyToOne(optional = false)
    private User siteCreator;
    @ManyToOne
    private RoutesNumber routesNumber;
    @ManyToOne
    private RockType rockType;
    @ManyToOne
    private Grade minGrade;
    @ManyToOne
    private Grade maxGrade;
    @ManyToOne(optional = false)
    private Department department;

    @ManyToMany
    @OrderBy
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LevelGroup> levelGroups = new HashSet<>();

    @ManyToMany
    @OrderBy
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Orientation> orientations = new HashSet<>();

    @ManyToMany
    @OrderBy
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SiteType> siteTypes = new HashSet<>();

    @ManyToMany(mappedBy = "sites")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Area> areas = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SiteContribution> contributions = new HashSet<>();

    @OneToMany(mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AddedTag> addedTags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @OrderBy("date DESC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments = new HashSet<>();

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setTownship(String township) {
        this.township = township.toLowerCase();
    }

    public String getSearchName() {
        return name.replace(" ", "_");
    }

    public Set<String> getAllAddedFieldsName() {
        Set<String> result = new HashSet<>();
        PropertyDescriptor propertyDescriptor;
        for (AlterableFieldEnum field : AlterableFieldEnum.values()) {
            String fieldName = field.toString();
            try {
                propertyDescriptor = new PropertyDescriptor(fieldName, this.getClass());
                Object fieldValue = propertyDescriptor.getReadMethod().invoke(this);
                if (fieldValue != null && !fieldValue.toString().equals("[]") && fieldValue != "") {
                    result.add(fieldName);
                }
            } catch (IntrospectionException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Set<String> getAllAddedFieldsName(SiteContributionDTO siteContributionDTO) {
        Set<String> result = new HashSet<>();
        PropertyDescriptor propertyDescriptor;
        for (AlterableFieldEnum field : AlterableFieldEnum.values()) {
            String fieldName = field.toString();
            try {
                propertyDescriptor = new PropertyDescriptor(fieldName, this.getClass());
                Object fieldValue = propertyDescriptor.getReadMethod().invoke(this);
                if (fieldValue == null || fieldValue.toString().equals("[]") || fieldValue.toString().equals("")) {
                    propertyDescriptor = new PropertyDescriptor(fieldName, siteContributionDTO.getClass());
                    fieldValue = propertyDescriptor.getReadMethod().invoke(siteContributionDTO);
                    if (fieldValue != null && !fieldValue.toString().equals("[]") && !fieldValue.toString().equals("")) {
                        result.add(fieldName);
                    }
                }
            } catch (IntrospectionException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Site merge(Site site) {
        this.setName(site.getName());
        this.setTownship(site.getTownship());
        this.setDepartment(site.getDepartment());
        this.setLatitude(site.getLatitude());
        this.setLongitude(site.getLongitude());
        this.setIsAcceptContribution(site.getIsAcceptContribution());
        this.setKidsFriendly(site.getKidsFriendly());
        this.setMinGrade(site.getMinGrade());
        this.setMaxGrade(site.getMaxGrade());
        this.setRockType(site.getRockType());
        this.setOrientations(site.getOrientations());
        this.setSiteTypes(site.getSiteTypes());
        this.setLevelGroups(site.getLevelGroups());
        this.setRoutesNumber(site.getRoutesNumber());
        this.setMaxRoutesHeight(site.getMaxRoutesHeight());
        this.setCragsNumber(site.getCragsNumber());
        this.setDescription(site.getDescription());
        this.setKidsFriendlyInfo(site.getKidsFriendlyInfo());
        this.setAdditionalInfos(site.getAdditionalInfos());
        this.setAccessInfo(site.getAccessInfo());
        this.setParkingLatitude(site.getParkingLatitude());
        this.setParkingLongitude(site.getParkingLongitude());

        return this;
    }

}
