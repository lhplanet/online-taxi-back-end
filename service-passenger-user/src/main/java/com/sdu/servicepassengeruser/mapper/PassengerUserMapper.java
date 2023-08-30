package com.sdu.servicepassengeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.internalcommon.dto.PassengerUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LHP
 * @description 乘客用户mapper
 */
@Repository
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {

    public List<PassengerUser> getPassengerUserList(@Param("para") PassengerUser para);

}
