package com.crypto.currency.crypto.viewer.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Pool{
    @JsonProperty("@type")
    public String type;
    public String address;
    public String id;
    public PoolParams poolParams;
    public String future_pool_governor;
    public TotalShares totalShares;
    public List<PoolAsset> poolAssets;
    public String totalWeight;
}
