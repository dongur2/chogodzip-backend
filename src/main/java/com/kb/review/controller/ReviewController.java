package com.kb.review.controller;

import com.kb.review.service.ReviewService;
import com.kb.review.vo.UserReview;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gptReview")
@RequiredArgsConstructor
@Slf4j
@PropertySource({"classpath:/application.properties"})
public class ReviewController {

    private final ReviewService reviewService;
    @GetMapping("/allReview")
    private ResponseEntity<List<UserReview>> getAllReview(long roomId) {
        List<UserReview> list = reviewService.getAllReview(roomId);
        return ResponseEntity.ok(list);
    }
}
