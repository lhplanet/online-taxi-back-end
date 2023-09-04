package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.EvaluateService;
import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.EvaluateVo;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
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
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;

    @PostMapping("/list")
    public ResultVo getEvaluateList(@RequestBody EvaluateVo evaluateVo){
        List<EvaluateVo> list = evaluateService.getEvaluateList(evaluateVo);
        return ResultUtils.success("查询成功", list);
    }
}
