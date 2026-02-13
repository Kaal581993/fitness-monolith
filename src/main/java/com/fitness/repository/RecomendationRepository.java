package com.fitness.repository;


import com.fitness.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendationRepository extends JpaRepository<Recommendation, String> {
    List<Recommendation> findByUserId(String userId);  // Traverses User -> id

    List<Recommendation> findByActivityId(String userId);
}
