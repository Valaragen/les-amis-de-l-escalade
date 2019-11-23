package com.rudy.ladl.service;

import com.rudy.ladl.entity.site.RoutesNumber;
import com.rudy.ladl.repository.RoutesNumberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoutesNumberService {
    private RoutesNumberRepository routesNumberRepository;

    public RoutesNumberService(RoutesNumberRepository routesNumberRepository) {
        this.routesNumberRepository = routesNumberRepository;
    }

    public List<RoutesNumber> findAll() {
        return routesNumberRepository.findAll();
    }
}
