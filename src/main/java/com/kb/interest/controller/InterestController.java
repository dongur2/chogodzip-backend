package com.kb.interest.controller;

import com.kb.interest.service.InterestService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interest")
@RequiredArgsConstructor
@Slf4j
@Api(value = "DetailController", tags = "방 상세 정보")
@PropertySource({"classpath:/application.properties"})
public class InterestController {

    private final InterestService interestService;



}
