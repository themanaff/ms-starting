package com.example.msusers.mapper;


import com.example.msusers.dao.entity.UsersEntity;
import com.example.msusers.dto.response.GetUserByIdResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    GetUserByIdResponse toResponse(UsersEntity userEntity);
}
