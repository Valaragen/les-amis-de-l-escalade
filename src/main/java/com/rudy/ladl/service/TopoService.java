package com.rudy.ladl.service;

import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.embeddable.CommentId;
import com.rudy.ladl.core.user.Topo;
import com.rudy.ladl.core.user.User;
import com.rudy.ladl.repository.CommentRepository;
import com.rudy.ladl.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public  class TopoService {

    private TopoRepository topoRepository;
    @Autowired
    public TopoService(TopoRepository topoRepository) {
        this.topoRepository = topoRepository;
    }

    public List<Topo> findAll() {
        return topoRepository.findAll();
    }

    public Topo addTopo(Topo topo, User user) {
        topo.setOwner(user);
        return topoRepository.saveAndFlush(topo);
    }

    public Topo findByName(String name) {
        return topoRepository.findByName(name.replace("_", " ")).orElse(null);
    }

    public List<Topo> findAllByOwner(User user) {
        return topoRepository.findAllByOwner(user);
    }
}
