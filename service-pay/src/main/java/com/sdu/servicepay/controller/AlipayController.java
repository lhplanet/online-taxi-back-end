package com.sdu.servicepay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.sdu.servicepay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LHP
 * @date 2023-07-14 23:56
 * @description
 */

@RequestMapping("/alipay")
@Controller
@ResponseBody
public class AlipayController {

    @GetMapping("/pay")
    public String pay(String subject,String outTradeNo, String totalAmount){
        AlipayTradePagePayResponse response ;
        try {
            response = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount,"http://localhost:3001/#/pages/evaluate");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return response.getBody();
    }

    @Autowired
    AlipayService alipayService;

    @PostMapping("/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("支付宝回调 notify");
        String tradeStatus = request.getParameter("trade_status");

        // 交易成功
        if (tradeStatus.trim().equals("TRADE_SUCCESS")){
            Map<String,String> param = new HashMap<>();

            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String name: parameterMap.keySet()) {
                param.put(name,request.getParameter(name));
            }

            // 验证签名
            if (Factory.Payment.Common().verifyNotify(param)){
                System.out.println("通过支付宝的验证");

                String out_trade_no = param.get("out_trade_no");
                Long orderId = Long.parseLong(out_trade_no);

                alipayService.pay(orderId);

//                redirectToFrontendPage(response);

                // 返回成功结果，通知支付宝
//                return "success";
                // 在支付成功后，生成跳转链接并返回给前端
                String redirectUrl = "http://localhost:3001/#/pages/evaluate"; // 替换成您的评价页面的路由
                return "redirect:" + redirectUrl;

            }else {
                System.out.println("支付宝验证 不通过！");
            }

        }

        return "failure"; // 返回失败结果，通知支付宝
    }

    /*@GetMapping("/redirectToFrontendPage")
    public void redirectToFrontendPage(HttpServletResponse response) throws IOException {
        // 设置重定向到前端页面的URL
        String frontendURL = "http://localhost:3001/#/pages/evaluate"; // 请替换为您的前端页面URL

        // 执行重定向
        response.sendRedirect(frontendURL);
    }*/
}
