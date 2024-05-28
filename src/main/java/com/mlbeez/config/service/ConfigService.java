package com.mlbeez.config.service;

import com.mlbeez.config.model.Property;

import java.util.List;

public interface ConfigService {
    public List<Property> getAllProperties();
    public Property getProperty(String key);
    public List<Property> getAllPropertiesByGroup(String group);

    public Property updatePropertyByKey(String key, String value);

    public void deleteProperty(String key);
}
