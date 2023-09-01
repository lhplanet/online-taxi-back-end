package com.sdu.serviceprice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.internalcommon.dto.PriceRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHP
 * @description 计价规则mapper
 */
@Repository
public interface PriceRuleMapper extends BaseMapper<PriceRule> {
    public List<PriceRule> getPriceRuleList(@Param("para") PriceRule para);
}
