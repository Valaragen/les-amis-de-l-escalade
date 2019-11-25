package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.user.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

}
