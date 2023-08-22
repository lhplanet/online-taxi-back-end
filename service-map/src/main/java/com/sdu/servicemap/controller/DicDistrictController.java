package com.sdu.servicemap.controller;

import com.sdu.internalcommon.dto.ResponseResult;
import com.sdu.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LHP
 * @date 2023-07-12 11:48
 * @description
 */

@RestController
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDicDistrict(String keywords) {
        return dicDistrictService.initDicDistrict(keywords);
    }

}
