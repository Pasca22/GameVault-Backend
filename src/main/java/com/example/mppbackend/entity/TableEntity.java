package com.example.mppbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableEntity {
    private Long gameOrderId;
    private String gameName;
    private String description;
    private Long userId;
    private String username;
}
