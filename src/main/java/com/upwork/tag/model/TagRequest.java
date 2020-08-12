package com.upwork.tag.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class TagRequest implements Serializable {
    private String id;
    private String tag;
    private String value;
}
