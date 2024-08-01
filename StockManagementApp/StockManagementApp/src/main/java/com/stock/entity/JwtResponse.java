package com.stock.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Integer userId;
    private String username;
    private String jwtToken;
    private String role;
}
