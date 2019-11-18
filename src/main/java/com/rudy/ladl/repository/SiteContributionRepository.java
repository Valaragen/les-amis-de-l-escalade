package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.SiteContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteContributionRepository extends JpaRepository<SiteContribution, Long> {

}
