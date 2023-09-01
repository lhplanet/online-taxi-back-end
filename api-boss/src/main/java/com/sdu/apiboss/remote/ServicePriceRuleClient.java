package com.sdu.apiboss.remote;

import com.sdu.internalcommon.dto.PriceRule;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@FeignClient("service-price")
public interface ServicePriceRuleClient {

    @RequestMapping(method = RequestMethod.POST, value = "/price-rule/add")
    public ResultVo addPriceRule(@RequestBody PriceRule priceRule);

    @RequestMapping(method = RequestMethod.POST, value = "/price-rule/update")
    public boolean updatePriceRule(@RequestBody PriceRule priceRule);

    @RequestMapping(method = RequestMethod.DELETE, value = "/price-rule/delete")
    public boolean deletePriceRule(@RequestBody PriceRule priceRule);

    @RequestMapping(method = RequestMethod.POST, value = "/price-rule/list")
    public List<PriceRule> getPriceRule(@RequestBody PriceRule priceRule);

}
