package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteContributionRepository extends JpaRepository<Site, Long> {

}
