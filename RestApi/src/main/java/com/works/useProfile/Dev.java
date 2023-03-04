package com.works.useProfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("dev")
public class Dev implements IConfig {

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("baseUrl", "dev.google.com");
        hm.put("apiKey", "dev_apikey_asdasdkajskdjasl");
        hm.put("rowCount", 50);
        return hm;
    }

    @Override
    public boolean control() {
        System.out.println("dev control");
        return false;
    }

}
