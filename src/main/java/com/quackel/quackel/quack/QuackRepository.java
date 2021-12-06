package com.quackel.quackel.quack;

import com.quackel.quackel.quack.Quack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuackRepository extends JpaRepository<Quack, Long> {//more longs?
}
