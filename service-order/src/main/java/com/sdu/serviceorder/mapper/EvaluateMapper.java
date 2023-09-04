package com.sdu.serviceorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.internalcommon.dto.Evaluate;
import com.sdu.internalcommon.dto.EvaluateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@Repository
public interface EvaluateMapper extends BaseMapper<Evaluate> {

    public List<EvaluateVo> getEvaluateList(@Param("para") EvaluateVo para);

}
