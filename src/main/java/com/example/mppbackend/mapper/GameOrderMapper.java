//package com.example.mppbackend.mapper;
//
//import com.example.mppbackend.dto.GameOrderDto;
//import com.example.mppbackend.entity.GameOrder;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface GameOrderMapper {
//    GameOrderDto toDto(GameOrder gameOrder);
//
//    List<GameOrderDto> toDtoList(List<GameOrder> gameOrders);
//
//    @Mapping(target = "user", ignore = true)
//    GameOrder toEntity(GameOrderDto gameOrderDTO);
//}