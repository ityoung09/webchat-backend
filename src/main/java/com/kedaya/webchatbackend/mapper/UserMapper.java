package com.kedaya.webchatbackend.mapper;

import com.kedaya.webchatbackend.model.dto.UserBaseInfoDTO;
import com.kedaya.webchatbackend.model.dto.UserLoginInfoRequestDTO;
import com.kedaya.webchatbackend.model.dto.UserRegisterInfoRequestDTO;
import com.kedaya.webchatbackend.model.entity.UserInfo;
import com.kedaya.webchatbackend.model.vo.UserBaseInfoVO;
import com.kedaya.webchatbackend.model.vo.UserLoginInfoRequestVO;
import com.kedaya.webchatbackend.model.vo.UserRegisterInfoRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegisterInfoRequestDTO registerVoToDto(UserRegisterInfoRequestVO userRegisterInfoRequestVO);

    UserLoginInfoRequestDTO loginVoToDto(UserLoginInfoRequestVO userLoginInfoRequestVO);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "createdDate", target = "registryTime")
    })
    UserBaseInfoDTO entityToBaseDto(UserInfo userInfo);


    UserBaseInfoVO baseDtoToVo(UserBaseInfoDTO userBaseInfoDto);

}
