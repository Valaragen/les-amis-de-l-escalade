package com.rudy.ladl.service;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.site.SiteContribution;
import com.rudy.ladl.repository.SiteContributionRepository;
import com.rudy.ladl.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SiteService {

    private SiteRepository siteRepository;
    private SiteContributionRepository siteContributionRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, SiteContributionRepository siteContributionRepository) {
        this.siteRepository = siteRepository;
        this.siteContributionRepository = siteContributionRepository;
    }

    public Site addSite(Site site) {
        return siteRepository.saveAndFlush(site);
    }

    public Page<Site> findAll() {
        Page<Site> sites = siteRepository.findAll(PageRequest.of(0,10));
        return sites;
    }

    public Site findByName(String name) {
        return siteRepository.findByName(name.replace("_", " ")).orElse(null);
    }
}
