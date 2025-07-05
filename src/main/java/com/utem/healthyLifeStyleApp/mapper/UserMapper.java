package com.utem.healthyLifeStyleApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.utem.healthyLifeStyleApp.dto.UserDTO;
import com.utem.healthyLifeStyleApp.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "password", ignore = true)
	@Mapping(target = "isFirstLogin", ignore = true)
	 User fromDTO(UserDTO dto);
	 UserDTO toDTO(User user);
}
