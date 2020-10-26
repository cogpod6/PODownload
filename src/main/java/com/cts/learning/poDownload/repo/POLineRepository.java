package com.cts.learning.poDownload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.learning.poDownload.model.POLineEntity;

@Repository
public interface POLineRepository extends JpaRepository<POLineEntity, Integer>{

}
