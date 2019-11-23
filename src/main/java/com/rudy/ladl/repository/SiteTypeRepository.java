package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Grade;
import com.rudy.ladl.entity.site.SiteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteTypeRepository extends JpaRepository<SiteType, Long> {

}
