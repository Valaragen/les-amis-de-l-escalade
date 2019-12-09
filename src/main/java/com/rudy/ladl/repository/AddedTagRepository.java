package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.AddedTag;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddedTagRepository extends JpaRepository<AddedTag, Long> {
    Optional<AddedTag> findByIdSiteAndIdTag(Site site, Tag tag);
}
