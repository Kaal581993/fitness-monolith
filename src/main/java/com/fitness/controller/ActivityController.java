package com.fitness.controller;


import com.fitness.dto.activity.ActivityTrackingResponse;
import com.fitness.dto.activity.SetActivityTrackingRequest;
import com.fitness.model.Activity;
import com.fitness.model.User;
import com.fitness.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/activities/")
@RequiredArgsConstructor

public class ActivityController {
    private final ActivityService activities;

    @PostMapping("/track/activities")
    public ResponseEntity<ActivityTrackingResponse> trackActivity(@RequestBody SetActivityTrackingRequest request){

        Activity activity = Activity.builder()
                .Duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .type(request.getType())
                .additionalMetrics(request.getAdditionalMetrics())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .recommendations(request.getRecommendations())
                .build();

        Activity saved = activities.activityTracking(activity);

        ActivityTrackingResponse response = new ActivityTrackingResponse();
        response.setDuration(saved.getDuration());
        response.setCaloriesBurned(saved.getCaloriesBurned());
        response.setType(saved.getType());
        response.setAdditionalMetrics(saved.getAdditionalMetrics());
        response.setStartTime(saved.getStartTime());
        response.setEndTime(saved.getEndTime());
        response.setCreatedAt(saved.getCreatedAt());
        response.setUpdatedAt(saved.getUpdatedAt());
        response.setRecommendations(saved.getRecommendations());

        return ResponseEntity.ok(response);
    }
}
