package com.quackel.quackel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuackRepository extends JpaRepository<Quack, Long> {//more longs?
}
