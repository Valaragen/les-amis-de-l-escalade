package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.SiteType;
import com.rudy.ladl.core.site.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String string);
}
