package com.mosaiker.adminservice.repository;

import com.mosaiker.adminservice.entity.Hean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeanRepository extends MongoRepository<Hean,String> {

}
