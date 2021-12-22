package com.crypto.currency.crypto.viewer.client;

import com.crypto.currency.crypto.viewer.client.dto.OsmosisPoolData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class OsmosisClient {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public OsmosisPoolData getPool(String id) throws IOException {
        Request req = new Request.Builder()
                .url("https://lcd-osmosis.keplr.app/osmosis/gamm/v1beta1/pools/" + id)
                .build();
        Response response = okHttpClient.newCall(req).execute();
        String json = response.body().string();
        return objectMapper.readValue(json, OsmosisPoolData.class);
    }
}
