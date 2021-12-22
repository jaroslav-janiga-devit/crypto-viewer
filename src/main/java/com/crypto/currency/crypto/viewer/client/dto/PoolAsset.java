package com.crypto.currency.crypto.viewer.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PoolAsset {
    public Token token;
    public String weight;
}
