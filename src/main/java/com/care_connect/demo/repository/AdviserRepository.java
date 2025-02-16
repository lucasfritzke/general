package com.care_connect.demo.repository;


import com.care_connect.demo.domain.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdviserRepository extends JpaRepository<Adviser, Long> {

    Optional<Adviser> findById(UUID id);

}

