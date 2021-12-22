package com.crypto.currency.crypto.viewer.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PoolParams{
    public String swapFee;
    public String exitFee;
    public Object smoothWeightChangeParams;
}
