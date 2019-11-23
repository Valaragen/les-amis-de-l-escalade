package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Department;
import com.rudy.ladl.entity.site.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
