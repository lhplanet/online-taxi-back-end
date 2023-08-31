package com.sdu.servicemap.controller;

import com.sdu.internalcommon.dto.DicDistrict;
import com.sdu.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author LHP
 * @date 2023-07-12 1:21
 * @description
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test service-map";
    }

    @Autowired
    DicDistrictMapper dicDistrictMapper;

    @GetMapping("/test-map")
    public String testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("address_code", "110000");
        List<DicDistrict> dicDistricts = dicDistrictMapper.selectByMap(map);
        System.out.println(dicDistricts);
        return "test service-map";
    }

}
