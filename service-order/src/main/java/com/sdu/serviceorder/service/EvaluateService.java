package com.sdu.serviceorder.service;

import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.EvaluateVo;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.serviceorder.mapper.EvaluateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@Service
@Slf4j
public class EvaluateService {

    @Autowired
    EvaluateMapper evaluateMapper;

    public ResponseResult add(Evaluate evaluate){
        int result = evaluateMapper.insert(evaluate);
        if(result == 1){
            return ResponseResult.success("评价成功");
        }
        return ResponseResult.fail("评价失败");
    }

    public List<EvaluateVo> getEvaluateList(EvaluateVo evaluateVo){
        return evaluateMapper.getEvaluateList(evaluateVo);
    }

}
