package com.transfer.utils;

import java.math.RoundingMode;
import java.util.Properties;

public class AppProperties extends Properties {

    public AppProperties(Properties defaults) {
        super(defaults);
    }

    public RoundingMode getAppRoundingMode() {
        return RoundingMode.valueOf(getProperty("app.roundingMode"));
    }

    public int getAppCurrencyScale() {
        return Integer.parseInt(getProperty("app.currency.scale"));
    }
}
