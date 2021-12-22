package com.crypto.currency.crypto.viewer.service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PoolData {
    private String id;
    private BigDecimal ratio;
}
