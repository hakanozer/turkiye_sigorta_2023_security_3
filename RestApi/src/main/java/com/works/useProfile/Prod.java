package com.works.useProfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("prod")
public class Prod implements IConfig {


    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("baseUrl", "prod.google.com");
        hm.put("apiKey", "prod_apikey_asdasdkajskdjasl");
        hm.put("rowCount", 20);
        return hm;
    }

    @Override
    public boolean control() {
        System.out.println("prod control");
        return false;
    }

}