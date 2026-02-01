package com.fitness.controller;


import com.fitness.dto.recommendation.RecommendationRequest;
import com.fitness.model.Recommendation;
import com.fitness.repository.RecomendationRepository;
import com.fitness.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recomendations")
@RequiredArgsConstructor
public class RecomendationController {

    private final RecomendationRepository recomendationRepository;

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody RecommendationRequest request
    ){

        Recommendation recommendation = recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

//    @GetMapping("/getrecommendation/{userId}")
//
//    @GetMapping("/ativityrecommendation")

}
