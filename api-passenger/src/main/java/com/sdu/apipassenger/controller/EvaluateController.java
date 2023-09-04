package com.sdu.apipassenger.controller;

import com.sdu.apipassenger.service.EvaluateService;
import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @description
 */
@RestController
@RequestMapping("/evaluate")
@Slf4j
public class EvaluateController {

    @Autowired
    EvaluateService evaluateService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Evaluate evaluate){
        return evaluateService.add(evaluate);
    }

}
