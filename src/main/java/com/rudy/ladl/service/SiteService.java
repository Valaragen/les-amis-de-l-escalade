package com.rudy.ladl.service;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.site.SiteContribution;
import com.rudy.ladl.entity.site.SiteField;
import com.rudy.ladl.entity.site.embeddable.SiteContributionId;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.repository.SiteContributionRepository;
import com.rudy.ladl.repository.SiteFieldRepository;
import com.rudy.ladl.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class SiteService {

    private SiteRepository siteRepository;
    private SiteContributionRepository siteContributionRepository;
    private SiteFieldRepository siteFieldRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, SiteContributionRepository siteContributionRepository, SiteFieldRepository siteFieldRepository) {
        this.siteRepository = siteRepository;
        this.siteContributionRepository = siteContributionRepository;
        this.siteFieldRepository = siteFieldRepository;
    }

    public Site addSite(Site site, User user) {
        Site savedSite = siteRepository.saveAndFlush(site);
        for (String field : site.getAllAddedFieldsName()) {
            SiteContribution siteContribution = new SiteContribution();

            SiteField siteField = siteFieldRepository.findByName(field);
            System.out.println(siteField);

            SiteContributionId siteContributionId = new SiteContributionId(siteField, savedSite);
            siteContribution.setId(siteContributionId);
            siteContribution.setUser(user);
            siteContributionRepository.save(siteContribution);
        }
        return site;
    }

    public Page<Site> findAll() {
        Page<Site> sites = siteRepository.findAll(PageRequest.of(0,10));
        return sites;
    }

    public Site findByName(String name) {
        return siteRepository.findByName(name.replace("_", " ")).orElse(null);
    }
}
