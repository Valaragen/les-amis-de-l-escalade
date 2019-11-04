package com.rudy.ladl.repository;

import com.rudy.ladl.entity.site.Department;
import com.rudy.ladl.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
