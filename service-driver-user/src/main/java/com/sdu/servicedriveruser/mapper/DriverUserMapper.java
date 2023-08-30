package com.sdu.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHP
 * @description 司机用户mapper
 */
@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {

    public int selectDriverUserCountByCityCode(@Param("cityCode") String cityCode);

    public List<DriverUser> getDriverUserList(@Param("para") DriverUser para);

}
