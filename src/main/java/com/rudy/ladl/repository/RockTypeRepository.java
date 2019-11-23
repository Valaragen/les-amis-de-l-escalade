package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Orientation;
import com.rudy.ladl.entity.site.RockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RockTypeRepository extends JpaRepository<RockType, Long> {

}
