package com.sdu.serviceorder.controller;

import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.EvaluateVo;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.serviceorder.service.EvaluateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/list")
    public List<EvaluateVo> getEvaluateList(@RequestBody EvaluateVo evaluateVo){
        return evaluateService.getEvaluateList(evaluateVo);
    }
}
