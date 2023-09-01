package com.sdu.apiboss.service;

import com.sdu.apiboss.remote.ServicePriceRuleClient;
import com.sdu.internalcommon.dto.PriceRule;
import com.sdu.internalcommon.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LHP
 * @description
 */
@Service
public class PriceRuleService {
    @Autowired
    ServicePriceRuleClient servicePriceRuleClient;

    public ResultVo addPriceRule(PriceRule priceRule){
        return servicePriceRuleClient.addPriceRule(priceRule);
    }

    public boolean updatePriceRule(PriceRule priceRule){
        return servicePriceRuleClient.updatePriceRule(priceRule);
    }

    public boolean deletePriceRule(PriceRule priceRule){
        return servicePriceRuleClient.deletePriceRule(priceRule);
    }

    public List<PriceRule> getPriceRule(PriceRule priceRule){
        return servicePriceRuleClient.getPriceRule(priceRule);
    }
}
