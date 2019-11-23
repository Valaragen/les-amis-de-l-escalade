package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Grade;
import com.rudy.ladl.entity.site.LevelGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelGroupRepository extends JpaRepository<LevelGroup, Long> {

}
