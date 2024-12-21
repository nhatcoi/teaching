package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

public class Pet {
    private Integer id;
    private String name;
    private Timestamp dob;
}
