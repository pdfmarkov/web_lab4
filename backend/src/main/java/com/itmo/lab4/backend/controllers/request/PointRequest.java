package com.itmo.lab4.backend.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointRequest{

    private String x;
    private String y;
    private String radius;
}
