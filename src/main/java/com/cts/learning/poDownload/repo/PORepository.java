package com.cts.learning.poDownload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.learning.poDownload.model.POEntity;

@Repository
public interface PORepository extends JpaRepository<POEntity, String> {

}
