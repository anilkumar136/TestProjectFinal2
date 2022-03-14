package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
