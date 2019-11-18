package com.rudy.ladl.entity.site;

import com.rudy.ladl.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
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
        BOTTOMROUTESALTITUDE("bottomRoutesAltitude"),
        MAXROUTESHEIGHT("maxRoutesHeight"),
        NEARESTVILLAGE("nearestVillage"),
        NEARESTBIGCITY("nearestBigCity"),
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
    @Column(precision=9, scale=6, nullable = false)
    private Double latitude;
    @NotNull
    @Column(precision=9, scale=6, nullable = false)
    private Double longitude;

    @Column(precision=9, scale=6)
    private Double parkingLatitude;
    @Column(precision=9, scale=6)
    private Double parkingLongitude;

    @Column
    private Boolean kidsFriendly;
    @Column(length = 5000)
    private String kidsFriendlyInfo;

    @Column
    private Integer cragsNumber;
    @Column
    private Integer bottomRoutesAltitude;
    @Column
    private Integer maxRoutesHeight;
    @Column(length = 50)
    private String nearestVillage;
    @Column(length = 50)
    private String nearestBigCity;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LevelGroup> levelGroups = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Orientation> orientations = new HashSet<>();

    @ManyToMany
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AddedTag> addedTags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id.site")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments = new HashSet<>();

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setTownship(String township) {
        this.township = township.toLowerCase();
    }

    public void setNearestVillage(String nearestVillage) {
        this.nearestVillage = nearestVillage.toLowerCase();
    }

    public void setNearestBigCity(String nearestBigCity) {
        this.nearestBigCity = nearestBigCity.toLowerCase();
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
                if(fieldValue != null && fieldValue.toString() != "[]") {
                    result.add(fieldName);
                }
            } catch (IntrospectionException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
