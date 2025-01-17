package com.mlbeez.config.repository;

import com.mlbeez.config.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepository extends JpaRepository<Property,Integer> {
    Property findByKey(String key);
    List<Property> findByGroups(String group);

    boolean existsByKey(String key);
    void deleteByKey(String key);
}
