package com.crypto.currency.crypto.viewer.service;

import com.crypto.currency.crypto.viewer.client.OsmosisClient;
import com.crypto.currency.crypto.viewer.client.dto.OsmosisPoolData;
import com.crypto.currency.crypto.viewer.client.dto.PoolAsset;
import com.crypto.currency.crypto.viewer.service.dto.PoolData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class OsmosisService {

    private final OsmosisClient osmosisClient;

    /**
     *  returns information about cryptocurrency pool and its ratio
     * @param id pool id in Osmosis
     * @return
     */
    public PoolData getPoolData(@NotBlank String id) throws IOException {
        OsmosisPoolData pool = osmosisClient.getPool(id);
        if(pool == null) throw new IllegalArgumentException("osmosis pool data not loaded");
        return PoolData.builder()
                .id(pool.getPool().getId())
                .ratio(computePoolRatio(pool))
                .build();
    }

    private BigDecimal computePoolRatio(OsmosisPoolData poolData){
        List<PoolAsset> poolAssets = poolData.getPool().getPoolAssets();
        if (CollectionUtils.isEmpty(poolAssets) || poolAssets.size() != 2){
            throw new IllegalArgumentException("not enough assets to compute ratio");
        }
        BigDecimal amount2 = new BigDecimal(poolAssets.get(1).getToken().getAmount());
        BigDecimal amount1 = new BigDecimal(poolAssets.get(0).getToken().getAmount());
        return amount2.divide(amount1,10, RoundingMode.DOWN);
    }
}
