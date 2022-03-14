package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
public class Player {
    @Id
    @JsonIgnore
    String id;
    String name;
    String playerID;
    String nameFirst;
    String height;
    String weight;
}
