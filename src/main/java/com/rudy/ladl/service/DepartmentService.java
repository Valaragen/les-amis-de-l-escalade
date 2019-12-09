package com.rudy.ladl.service;

import com.rudy.ladl.core.site.Department;
import com.rudy.ladl.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService (DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
