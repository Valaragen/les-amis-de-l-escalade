package com.rudy.ladl.service;

import com.rudy.ladl.core.site.Orientation;
import com.rudy.ladl.repository.OrientationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrientationService {
    private OrientationRepository orientationRepository;

    public OrientationService(OrientationRepository orientationRepository) {
        this.orientationRepository = orientationRepository;
    }

    public List<Orientation> findAll() {
        return orientationRepository.findAll();
    }
}
