package com.example.mppbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameOrderDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long userId;
}
