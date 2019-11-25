package com.rudy.ladl.service;

import com.rudy.ladl.core.site.Grade;
import com.rudy.ladl.repository.GradeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class GradeService {
    private GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }
}
