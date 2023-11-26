package com.progressoft.warehouse.repository;

import com.progressoft.warehouse.entity.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Repository
public interface DealRepository extends JpaRepository<DealEntity, String> {

}
