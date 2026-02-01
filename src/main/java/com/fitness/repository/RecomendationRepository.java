package com.fitness.repository;


import com.fitness.model.Recommendation;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendationRepository extends JpaRepository<Recommendation, String> {
}
