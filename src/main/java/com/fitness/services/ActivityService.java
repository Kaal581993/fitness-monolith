package com.fitness.services;

import com.fitness.model.Activity;
import com.fitness.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public Activity activityTracking(Activity activity) {
        Activity activityTracking = Activity.builder()
                .Duration(activity.getDuration())
                .caloriesBurned(activity.getCaloriesBurned())
                .type(activity.getType())
                .additionalMetrics(activity.getAdditionalMetrics())
                .startTime(activity.getStartTime())
                .endTime(activity.getEndTime())
                .recommendations(activity.getRecommendations())
                .build();

        return activityRepository.save(activityTracking);
    }
}
