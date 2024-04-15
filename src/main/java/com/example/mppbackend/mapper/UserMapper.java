//package com.example.mppbackend.mapper;
//
//import com.example.mppbackend.dto.GameOrderDto;
//import com.example.mppbackend.dto.UserDto;
//import com.example.mppbackend.entity.GameOrder;
//import com.example.mppbackend.entity.User;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    UserDto toDto(User user);
//
//    List<UserDto> toDtoList(List<User> users);
//
//    @Mapping(target = "gameOrders", ignore = true)
//    User toEntity(UserDto userDTO);
//
//    @Mapping(target = "user", source = "userId")
//    GameOrder toEntity(GameOrderDto gameOrderDTO);
//
//    List<GameOrder> toEntityList(List<GameOrderDto> gameOrderDTOs);
//}
