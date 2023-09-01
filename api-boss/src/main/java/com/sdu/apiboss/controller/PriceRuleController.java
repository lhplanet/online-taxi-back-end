package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.PriceRuleService;
import com.sdu.internalcommon.dto.PriceRule;
import com.sdu.internalcommon.result.ResultUtils;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Autowired
    PriceRuleService priceRuleService;

    /**
     * 添加计价规则
     * @param priceRule 计价规则
     * @return 响应结果
     */
    @PostMapping("/add")
    public ResultVo addPriceRule(@RequestBody PriceRule priceRule){
        return priceRuleService.addPriceRule(priceRule);
    }

    /**
     * 修改计价规则
     * @param priceRule 计价规则
     * @return 响应结果
     */
    @PostMapping("/edit")
    public ResultVo updatePriceRule(@RequestBody PriceRule priceRule){
        boolean result = priceRuleService.updatePriceRule(priceRule);
        if(!result){
            return ResultUtils.error("编辑失败!");
        }
        return ResultUtils.success("编辑成功!");
    }

    /**
     * 删除计价规则
     * @param
     * @return 响应结果
     */
    @PostMapping("/delete")
    public ResultVo deletePriceRule(@RequestBody PriceRule priceRule){
        boolean result = priceRuleService.deletePriceRule(priceRule);
        if(!result){
            return ResultUtils.error("删除失败!");
        }
        return ResultUtils.success("删除成功!");
    }

    /**
     * 查询计价规则列表
     * @param priceRule 计价规则
     * @return 响应结果
     */
    @PostMapping("/list")
    public ResultVo getPriceRule(@RequestBody PriceRule priceRule){
        List<PriceRule> list = priceRuleService.getPriceRule(priceRule);
        return ResultUtils.success("查询成功", list);
    }

}
