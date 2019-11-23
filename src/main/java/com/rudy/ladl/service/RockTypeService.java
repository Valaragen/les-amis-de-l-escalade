package com.rudy.ladl.service;

import com.rudy.ladl.entity.site.Orientation;
import com.rudy.ladl.entity.site.RockType;
import com.rudy.ladl.repository.OrientationRepository;
import com.rudy.ladl.repository.RockTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RockTypeService {
    private RockTypeRepository rockTypeRepository;

    public RockTypeService(RockTypeRepository rockTypeRepository) {
        this.rockTypeRepository = rockTypeRepository;
    }

    public List<RockType> findAll() {
        return rockTypeRepository.findAll();
    }
}
