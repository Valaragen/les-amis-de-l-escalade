package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.SiteContribution;
import com.rudy.ladl.core.site.SiteField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteContributionRepository extends JpaRepository<SiteContribution, Long> {
    Optional<SiteContribution> findByIdFieldAndIdSite(SiteField id_field, Site id_site);
}
