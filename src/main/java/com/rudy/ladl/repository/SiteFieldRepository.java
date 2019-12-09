package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.SiteField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteFieldRepository extends JpaRepository<SiteField, Long> {
    SiteField findByName(String name);
}
