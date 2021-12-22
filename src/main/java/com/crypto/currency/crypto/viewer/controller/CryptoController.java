package com.crypto.currency.crypto.viewer.controller;

import com.crypto.currency.crypto.viewer.client.dto.OsmosisPoolData;
import com.crypto.currency.crypto.viewer.controller.dto.CurrencyPoolRatio;
import com.crypto.currency.crypto.viewer.mapper.CurrencyConversionMapper;
import com.crypto.currency.crypto.viewer.service.OsmosisService;
import com.crypto.currency.crypto.viewer.service.dto.PoolData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@RequestMapping("/api")
@AllArgsConstructor
public class CryptoController {

    private final OsmosisService osmosisService;
    private final CurrencyConversionMapper currencyConversionMapper;

    @GetMapping(path = "/pool/{id}")
    public ResponseEntity<CurrencyPoolRatio> helloWorld(@PathVariable() String id) throws IOException {
        PoolData poolData = osmosisService.getPoolData(id);
        CurrencyPoolRatio convertedCurrency = currencyConversionMapper.from(poolData);
        return ResponseEntity.ok(convertedCurrency);
    }
}
