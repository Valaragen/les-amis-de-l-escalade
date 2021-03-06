package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.user.Topo;
import com.rudy.ladl.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

    Optional<Topo> findByName(String name);

    List<Topo> findAllByOwner(User user);

    Set<Topo> findAllByBookingAskersContainingAndStatusIsTrue(User user);

    Set<Topo> findAllByBookingAskersContainingAndStatusIsFalse(User user);

    Set<Topo> findAllByOwnerAndBookingAskersIsNotNullAndStatusIsTrue(User user);

    Set<Topo> findAllByOwnerAndBookingAskersIsNotNullAndStatusIsFalse(User user);
}
