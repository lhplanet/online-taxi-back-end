package com.sdu.serviceprice.controller;

import com.sdu.internalcommon.dto.PriceRule;
import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.internalcommon.request.PriceRuleIsNewRequest;
import com.sdu.internalcommon.result.ResultVo;
import com.sdu.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LHP
 * @description 计价规则controller
 */
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Autowired
    PriceRuleService priceRuleService;

    @PostMapping("/add")
    public ResultVo addPriceRule(@RequestBody PriceRule priceRule){
        return priceRuleService.addPriceRule(priceRule);
    }

    @PostMapping("/update")
    public boolean updatePriceRule(@RequestBody PriceRule priceRule){
        return priceRuleService.updatePriceRule(priceRule);
    }

    @DeleteMapping("/delete")
    public boolean deleteRequestBody(@RequestBody PriceRule priceRule){
        return priceRuleService.deletePriceRule(priceRule);
    }

    @PostMapping("/list")
    public List<PriceRule> getPriceRuleList(@RequestBody PriceRule priceRule){
        return priceRuleService.getPriceRuleList(priceRule);
    }

    /**
     * 查询最新的计价规则
     * @param fareType
     * @return
     */
    @GetMapping("/get-newest-version")
    public ResponseResult<PriceRule> getNewestVersion(@RequestParam String fareType){
        return priceRuleService.getNewestVersion(fareType);
    }

    /**
     * 判断规则是否是最新
     * @param priceRuleIsNewRequest
     * @return
     */
    @PostMapping("/is-new")
    public ResponseResult<Boolean> isNew(@RequestBody PriceRuleIsNewRequest priceRuleIsNewRequest){
        return priceRuleService.isNew(priceRuleIsNewRequest.getFareType(),priceRuleIsNewRequest.getFareVersion());
    }

    /**
     * 判断该城市和对应车型的计价规则是否存在
     * @param priceRule
     * @return
     */
    @PostMapping("/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){
        return priceRuleService.ifExists(priceRule);
    }
}
