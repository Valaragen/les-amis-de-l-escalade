package com.rudy.ladl.service;

import com.rudy.ladl.core.SiteSearch;
import com.rudy.ladl.core.dto.SiteContributionDTO;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.SiteContribution;
import com.rudy.ladl.core.site.SiteField;
import com.rudy.ladl.core.site.embeddable.SiteContributionId;
import com.rudy.ladl.core.user.User;
import com.rudy.ladl.exception.SiteFieldAlreadyFilledException;
import com.rudy.ladl.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SiteService {

    private SiteRepository siteRepository;
    private SiteContributionRepository siteContributionRepository;
    private SiteFieldRepository siteFieldRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, SiteContributionRepository siteContributionRepository,
                       SiteFieldRepository siteFieldRepository) {
        this.siteRepository = siteRepository;
        this.siteContributionRepository = siteContributionRepository;
        this.siteFieldRepository = siteFieldRepository;
    }

    public Site addSite(Site site, User user) {
        site.setSiteCreator(user);
        Site savedSite = siteRepository.saveAndFlush(site);
        for (String field : site.getAllAddedFieldsName()) {
            SiteContribution siteContribution = new SiteContribution();

            SiteField siteField = siteFieldRepository.findByName(field);

            SiteContributionId siteContributionId = new SiteContributionId(siteField, savedSite);
            siteContribution.setId(siteContributionId);
            siteContribution.setUser(user);
            siteContributionRepository.save(siteContribution);
        }
        return site;
    }

    public Site modifySite(Site site) {
        siteRepository.save(site);
        return site;
    }

    public void deleteSite(Site site) {
        siteRepository.delete(site);
    }

    public void addContribution(Site site, SiteContributionDTO siteContributionDTO, User user) throws SiteFieldAlreadyFilledException {
        int n = 0;
        for (String field : site.getAllAddedFieldsName(siteContributionDTO)) {
            n++;
            SiteContribution siteContribution = new SiteContribution();
            SiteField siteField = siteFieldRepository.findByName(field);

            SiteContributionId siteContributionId = new SiteContributionId(siteField, site);
            siteContribution.setId(siteContributionId);
            siteContribution.setUser(user);
            siteContributionRepository.save(siteContribution);
        }
        if (n > 0) {
            siteRepository.save(siteContributionDTO.mergeInSite(site));
        }
    }

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public List<Site> search(SiteSearch siteSearch) {
        return siteRepository.findAllBySearch(siteSearch);
    }

    public Site findByName(String name) {
        return siteRepository.findByName(name.replace("_", " ")).orElse(null);
    }

    public Site findById(Long id) {
        return siteRepository.findById(id).orElse(null);
    }
}
