package com.rudy.ladl.service;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.repository.SiteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SiteService {

    private SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public void addSite() {

    }

    public Page<Site> findAll() {
        Page<Site> sites = siteRepository.findAll(PageRequest.of(0,10));
        return sites;
    }
}
