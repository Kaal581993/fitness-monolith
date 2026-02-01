package com.fitness.controller;


import com.fitness.dto.activity.ActivityTrackingResponse;
import com.fitness.dto.activity.SetActivityTrackingRequest;
import com.fitness.model.Activity;
import com.fitness.model.User;
import com.fitness.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/activities/")
@RequiredArgsConstructor

public class ActivityController {
    private final ActivityService activities;

    @PostMapping("/track/setactivities")
    public ResponseEntity<ActivityTrackingResponse> trackActivity(@RequestBody SetActivityTrackingRequest request){
        return ResponseEntity.ok(activities.activityTracking(request));
    }

    @GetMapping("get")
    public ResponseEntity<List<ActivityTrackingResponse>> getActivities(
           @RequestHeader(value = "X-User-ID") String userId

    ){
        return ResponseEntity.ok(activities.getActivities(userId));
    }
}
