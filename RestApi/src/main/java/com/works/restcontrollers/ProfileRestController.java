package com.works.restcontrollers;

import com.works.useProfile.IConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileRestController {

    final IConfig iConfig;

    @GetMapping("/config")
    public Map config() {
        iConfig.control();
        return iConfig.config();
    }


}
