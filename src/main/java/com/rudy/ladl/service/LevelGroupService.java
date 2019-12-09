package com.rudy.ladl.service;

import com.rudy.ladl.core.site.LevelGroup;
import com.rudy.ladl.repository.LevelGroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class LevelGroupService {
    private LevelGroupRepository levelGroupRepository;

    public LevelGroupService(LevelGroupRepository levelGroupRepository) {
        this.levelGroupRepository = levelGroupRepository;
    }

    public List<LevelGroup> findAll() {
        return levelGroupRepository.findAll();
    }
}
