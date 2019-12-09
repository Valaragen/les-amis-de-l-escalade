package com.rudy.ladl.repository;

import com.rudy.ladl.core.SiteSearch;
import com.rudy.ladl.core.site.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class SiteRepositoryCustomImpl implements SiteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Site> findAllBySearch(SiteSearch siteSearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Site> query = cb.createQuery(Site.class);
        Root<Site> site = query.from(Site.class);

        Path<String> namePath = site.get("name");

        List<Predicate> predicates = new ArrayList<>();


        if (siteSearch.getName() != null) {
            predicates.add(cb.like(namePath, "%" + siteSearch.getName() + "%"));
        } else {
            predicates.add(cb.like(namePath, "%"));
        }

        if (siteSearch.getKidsFriendly() != null) {
            Path<Boolean> kidsFriendlyPath = site.get("kidsFriendly");
            if (siteSearch.getKidsFriendly()) {
                predicates.add(cb.isTrue(kidsFriendlyPath));
            }
        }

        if (siteSearch.getDepartmentId() != null) {
            Path<Department> departmentPath = site.get("department");
            predicates.add(cb.equal(departmentPath.get("id"), siteSearch.getDepartmentId()));
        }

        if (siteSearch.getGradeId() != null) {
            Path<Grade> minGradePath = site.get("minGrade");
            Path<Grade> maxGradePath = site.get("maxGrade");
            predicates.add(cb.greaterThanOrEqualTo(maxGradePath.get("id"), siteSearch.getGradeId()));
            predicates.add(cb.lessThanOrEqualTo(minGradePath.get("id"), siteSearch.getGradeId()));
        }

        if(siteSearch.getSiteTypeId() != null) {
            Expression siteTypesInExp = site.join("siteTypes").get("id");
            predicates.add(siteTypesInExp.in(siteSearch.getSiteTypeId()));
        }

        if(siteSearch.getLevelGroupId() != null) {
            Expression levelGroupsInExp = site.join("levelGroups").get("id");
            predicates.add(levelGroupsInExp.in(siteSearch.getLevelGroupId()));
        }

        if (siteSearch.getRoutesNumberId() != null) {
            Path<RoutesNumber> routesNumberPath = site.get("routesNumber");
            predicates.add(cb.equal(routesNumberPath.get("id"), siteSearch.getRoutesNumberId()));
        }

        if (siteSearch.getMinRouteHeight() != null) {
            Path<Integer> maxRouteHeightPath = site.get("maxRoutesHeight");
            predicates.add(cb.greaterThanOrEqualTo(maxRouteHeightPath, siteSearch.getMinRouteHeight()));
        }

        query.select(site)
                .where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
