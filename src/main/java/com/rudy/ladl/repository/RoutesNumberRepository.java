package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.RoutesNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesNumberRepository extends JpaRepository<RoutesNumber, Long> {

}
