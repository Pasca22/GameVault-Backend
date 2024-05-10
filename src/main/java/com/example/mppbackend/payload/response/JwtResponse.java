package com.example.mppbackend.payload.response;

import com.example.mppbackend.entity.GameOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private List<GameOrder> gameOrders;

    public JwtResponse(String accessToken, Long id, String username, String email, List<GameOrder> gameOrders, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.gameOrders = gameOrders;
        this.roles = roles;
    }

}
