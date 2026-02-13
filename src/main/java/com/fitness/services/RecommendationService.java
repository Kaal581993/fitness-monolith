package com.fitness.services;


import com.fitness.dto.recommendation.RecommendationRequest;
import com.fitness.dto.recommendation.RecommendationResponse;
import com.fitness.model.Activity;
import com.fitness.model.Recommendation;
import com.fitness.model.User;
import com.fitness.repository.ActivityRepository;
import com.fitness.repository.RecomendationRepository;
import com.fitness.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationService {
    private final UserRepository userRepository;

    private final RecomendationRepository recomendationRepository;

    private final ActivityRepository activityRepository;
    public Recommendation generateRecommendation(RecommendationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User Not Found with User ID:"+request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity Not Found with ID:"+request.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .type(request.getType())
                .recommendation(request.getRecommendation())
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();


        //Recommendation savedRecommendation = recomendationRepository.save(recommendation);

        return recomendationRepository.save(recommendation);

    }

    public List<Recommendation> getRecommendation(String userId){
        return recomendationRepository.findByUserId(userId);
    }


    public List<Recommendation> getActivityRecommendation(String activityId){
        return recomendationRepository.findByActivityId(activityId);
    }
}
