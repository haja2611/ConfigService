package com.mlbeez.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
@Table(name = "property")
public class Property extends RepresentationModel<Property> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use GenerationType.IDENTITY for auto-generated IDs
    @Column(columnDefinition = "bigint")
    private Long id;
    private String group;
    private String key;
    private String value;
    private String description;

}
