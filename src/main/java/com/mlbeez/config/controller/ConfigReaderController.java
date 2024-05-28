package com.mlbeez.config.controller;

import com.mlbeez.config.model.Property;
import com.mlbeez.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigReaderController {

    @Autowired
    ConfigService configService;
    @GetMapping("/config/{key}")
    public Property getConfig(@PathVariable("key") String key) {
        return configService.getProperty(key);
    }

    @GetMapping("/config")
    public List<Property> getConfig() {
        return configService.getAllProperties();
    }

    @GetMapping("/config/group/{group}")
    public List<Property> getConfigByGroup(@PathVariable("group") String group) {
        return configService.getAllPropertiesByGroup(group);
    }

    @PutMapping("/config/{key}/value/{value}")
    public Property updateConfigByKey(@PathVariable("key") String key, @PathVariable("value") String value) {
        return configService.updatePropertyByKey(key,value);
    }

    @DeleteMapping("/config/{key}")
    public Property deleteConfig(@PathVariable("key") String key) {
        return configService.getProperty(key);
    }

}
