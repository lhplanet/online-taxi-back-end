package com.sdu.servicedriveruser.mapper;

import com.sdu.internalcommon.dto.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHP
 * @description 车辆mapper
 */
@Repository
public interface CarMapper extends BaseMapper<Car> {

    public List<Car> getCarList(@Param("para") Car para);

    public int getCarCount();

}
