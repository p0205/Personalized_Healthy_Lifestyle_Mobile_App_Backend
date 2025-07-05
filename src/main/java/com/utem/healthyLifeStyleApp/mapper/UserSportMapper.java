package com.utem.healthyLifeStyleApp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.utem.healthyLifeStyleApp.dto.UserSportDTO;
import com.utem.healthyLifeStyleApp.model.UserSport;

@Mapper(componentModel = "spring")
public interface UserSportMapper {

    @Mapping(target = "sport", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserSport fromDto(UserSportDTO dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "sportId", source = "sport.id")
    @Mapping(target = "sportName", source = "sport.name")
    UserSportDTO toDto(UserSport userSport);
    List<UserSportDTO> toDtoList(List<UserSport> userSportList);
}
