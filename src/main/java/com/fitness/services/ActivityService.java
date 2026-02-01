package com.fitness.services;

import com.fitness.dto.activity.ActivityTrackingResponse;
import com.fitness.dto.activity.SetActivityTrackingRequest;
import com.fitness.model.Activity;
import com.fitness.model.User;
import com.fitness.repository.ActivityRepository;
import com.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityTrackingResponse activityTracking(SetActivityTrackingRequest activityRequest) {

        User user= (User) userRepository.findById(activityRequest.getUserId())
                .orElseThrow(()->new RuntimeException("User Not found"+ activityRequest.getUserId()));
        Activity activityTracking = Activity.builder()
                .Duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .type(activityRequest.getType())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .user(user)
                .startTime(activityRequest.getStartTime())
                .endTime(activityRequest.getEndTime())
                .build();


        Activity savedActivity = activityRepository.save(activityTracking);
        return mapToResponse(savedActivity);


   //     return activityRepository.save(activityTracking);
    }

    private ActivityTrackingResponse mapToResponse(Activity savedActivity) {

        //Activity saved = activities.activityTracking(activity);

        ActivityTrackingResponse response = new ActivityTrackingResponse();
        response.setId(savedActivity.getId());
        response.setUserID(savedActivity.getUser().getId());
        response.setDuration(savedActivity.getDuration());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setType(savedActivity.getType());
        response.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
        response.setStartTime(savedActivity.getStartTime());
        response.setEndTime(savedActivity.getEndTime());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setUpdatedAt(savedActivity.getUpdatedAt());
        response.setRecommendations(savedActivity.getRecommendations());
        return response;
    }


    public @Nullable List<ActivityTrackingResponse> getActivities(String userId) {

        List<Activity> activityList = activityRepository.findByUserId(userId);
//        ActivityTrackingResponse response = new ActivityTrackingResponse();
//
//        response.getId();
//        response.getUserID();
//        response.getDuration();
//        response.getCaloriesBurned();
//        response.getType();
//        response.getAdditionalMetrics();
//        response.getStartTime();
//        response.getEndTime();
//        response.getCreatedAt();
//        response.getUpdatedAt();
//        response.getRecommendations();

        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }
}
