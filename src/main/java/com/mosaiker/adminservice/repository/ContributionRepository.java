package com.mosaiker.adminservice.repository;

import com.mosaiker.adminservice.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    Contribution findByCId(Long cId);
}
