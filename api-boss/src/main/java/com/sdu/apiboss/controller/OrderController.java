package com.sdu.apiboss.controller;

import com.sdu.apiboss.service.OrderService;
import com.sdu.internalcommon.dto.OrderInfo;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/list")
    public ResultVo getOrderList(@RequestBody OrderInfo orderInfo){
        List<OrderInfo> list = orderService.getOrderList(orderInfo);
        return ResultUtils.success("查询成功", list);
    }

}
