package com.crypto.currency.crypto.viewer.mapper;

import com.crypto.currency.crypto.viewer.controller.dto.CurrencyPoolRatio;
import com.crypto.currency.crypto.viewer.service.dto.PoolData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CurrencyConversionMapper {

    @Mapping(source = "id",target = "poolId")
    @Mapping(source = "ratio",target = "ratio")
    public abstract CurrencyPoolRatio from(PoolData data);

}
