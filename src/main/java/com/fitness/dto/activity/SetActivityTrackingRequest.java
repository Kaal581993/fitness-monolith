package com.fitness.dto.activity;

import com.fitness.model.ActivityType;
import com.fitness.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetActivityTrackingRequest {
    private String userId;
    private Integer Duration;
    private Integer caloriesBurned;
    private ActivityType type;
    private Map<String, Object> additionalMetrics;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
   // private List<Recommendation> recommendations = new ArrayList<>();


}
