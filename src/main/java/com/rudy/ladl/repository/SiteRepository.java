package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    Optional<Site> findByName(String name);
}
