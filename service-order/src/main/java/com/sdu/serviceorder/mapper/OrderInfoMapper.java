package com.sdu.serviceorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.internalcommon.dto.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHP
 * @description 订单mapper
 */
@Repository
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    public List<OrderInfo> getOrderList(@Param("para") OrderInfo para);

}
