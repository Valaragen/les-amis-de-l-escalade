package com.rudy.ladl.repository;

import com.rudy.ladl.core.SiteSearch;
import com.rudy.ladl.core.site.Site;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepositoryCustom {
    List<Site> findAllBySearch(SiteSearch siteSearch);
}
