package com.example.forexinformation.forexRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forexinformation.entity.ForexEntity;

@Repository
public interface ForexRepository extends JpaRepository<ForexEntity, String> {

    List<ForexEntity> findByTransactionDateBetween(String startDate, String endDate) ;

}
