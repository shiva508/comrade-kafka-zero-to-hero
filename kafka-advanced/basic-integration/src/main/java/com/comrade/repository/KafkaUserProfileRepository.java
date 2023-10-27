package com.comrade.repository;

import com.comrade.entity.KafkaUserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaUserProfileRepository extends JpaRepository<KafkaUserProfileEntity, Integer> {

}
