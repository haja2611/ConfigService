package com.mlbeez.config.service;

import com.mlbeez.config.controller.ConfigReaderController;
import com.mlbeez.config.model.Property;
import com.mlbeez.config.repository.ConfigRepository;
import com.mlbeez.config.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService{

    @Autowired
    ConfigRepository configRepository;
    public List<Property> getAllProperties() {
        List<Property> properties = configRepository.findAll();
        if (CollectionUtils.isEmpty(properties)) {
            throw new DataNotFoundException("No properties available");
        }

        for (Property property : properties) {
            Link selfLink = WebMvcLinkBuilder.linkTo(ConfigReaderController.class).withSelfRel();
            property.add(selfLink);
        }
        Link link = WebMvcLinkBuilder.linkTo(ConfigReaderController.class).withSelfRel();
        CollectionModel<Property> result = CollectionModel.of(properties, link);
        return properties;
    }

    public Property getProperty(String key) {
        Property property = configRepository.findByKey(key);
        if (ObjectUtils.isEmpty(property)) {
            throw new DataNotFoundException("No property available for the key "+key);
        }
        Link selfLink = WebMvcLinkBuilder.linkTo(ConfigReaderController.class).withSelfRel();
        property.add(selfLink);
        return property;
    }

    public List<Property> getAllPropertiesByGroup(String group) {
        List<Property> properties = configRepository.findByGroup(group);
        if (CollectionUtils.isEmpty(properties)) {
            throw new DataNotFoundException("No properties available for the group, "+group);
        }

        for (Property property : properties) {
            Link selfLink = WebMvcLinkBuilder.linkTo(ConfigReaderController.class).withSelfRel();
            property.add(selfLink);
        }
        Link link = WebMvcLinkBuilder.linkTo(ConfigReaderController.class).withSelfRel();
        CollectionModel<Property> result = CollectionModel.of(properties, link);
        return properties;
    }

    @Override
    public Property updatePropertyByKey(String key, String value) {
        Property property = configRepository.findByKey(key);
        if (ObjectUtils.isEmpty(property)) {
            throw new DataNotFoundException("No property available for the key "+key);
        }
        property.setValue(value);
        property = configRepository.save(property);
        Link selfLink = WebMvcLinkBuilder.linkTo(ConfigReaderController.class).withSelfRel();
        property.add(selfLink);
        return property;
    }

    @Override
    public void deleteProperty(String key) {
        if (configRepository.existsByKey(key)) {
            configRepository.deleteByKey(key);
            return;
        }
        throw new DataNotFoundException("No property available for the key "+key);
    }

}
