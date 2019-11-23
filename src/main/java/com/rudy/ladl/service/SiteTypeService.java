package com.rudy.ladl.service;

import com.rudy.ladl.entity.site.Grade;
import com.rudy.ladl.entity.site.SiteType;
import com.rudy.ladl.repository.GradeRepository;
import com.rudy.ladl.repository.SiteTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SiteTypeService {
    private SiteTypeRepository siteTypeRepository;

    public SiteTypeService(SiteTypeRepository siteTypeRepository) {
        this.siteTypeRepository = siteTypeRepository;
    }

    public List<SiteType> findAll() {
        return siteTypeRepository.findAll();
    }
}
