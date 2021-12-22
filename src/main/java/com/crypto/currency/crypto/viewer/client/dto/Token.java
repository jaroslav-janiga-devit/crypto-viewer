package com.crypto.currency.crypto.viewer.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token{
    public String denom;
    public String amount;
}
