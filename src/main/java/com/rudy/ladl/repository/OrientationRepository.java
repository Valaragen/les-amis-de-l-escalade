package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.Orientation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientationRepository extends JpaRepository<Orientation, Long> {

}
