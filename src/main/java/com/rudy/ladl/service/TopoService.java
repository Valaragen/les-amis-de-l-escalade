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
import java.util.Set;

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

    public Topo findById(Long id) {
        return topoRepository.findById(id).orElse(null);
    }

    public List<Topo> findAllByOwner(User user) {
        return topoRepository.findAllByOwner(user);
    }

    public Set<Topo> findAllReservationsFromUser(User user) {
        return topoRepository.findAllByBookingAskersContainingAndStatusIsTrue(user);
    }

    public Set<Topo> findAllReservationsToUser(User user) {
        return topoRepository.findAllByOwnerAndBookingAskersIsNotNullAndStatusIsTrue(user);
    }

    public Set<Topo> findAllLendFromUser(User user) {
        return topoRepository.findAllByBookingAskersContainingAndStatusIsFalse(user);
    }

    public Set<Topo> findAllLendToUser(User user) {
        return topoRepository.findAllByOwnerAndBookingAskersIsNotNullAndStatusIsFalse(user);
    }

    public Boolean addReservation(Topo topo, User user) {
        if(topo.getOwner().getId().equals(user.getId())) {
            return false;
        }

        topo.getBookingAskers().add(user);
        topoRepository.save(topo);
        return true;
    }

    public Boolean removeReservationFromUser(Topo topo, User user) {
        topo.getBookingAskers().remove(user);
        topoRepository.save(topo);
        return true;
    }

    public Boolean acceptReservationFromUser(Topo topo, User user) {
        if (!topo.getBookingAskers().contains(user)) {
            return false;
        }
        topo.getBookingAskers().removeIf(bookingUser -> !user.equals(bookingUser));
        topo.setStatus(false);
        topoRepository.save(topo);
        return true;
    }

    public void setTopoAvailable(Topo topo, User user) {
        topo.setStatus(true);
        topo.setBookingAskers(null);
        topoRepository.save(topo);
    }

    public void setTopoNotAvailable(Topo topo, User user) {
        topo.setStatus(false);
        topo.setBookingAskers(null);
        topoRepository.save(topo);
    }

    public Topo modifyTopo(Topo topo) {
        topoRepository.save(topo);
        return topo;
    }

    public void deleteTopo(Topo topo) {
        topoRepository.delete(topo);
    }
}
