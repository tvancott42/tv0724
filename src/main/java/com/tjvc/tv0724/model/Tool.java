package com.tjvc.tv0724.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Note: Could be easily converted to a JPA Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tool {

    public static final String DELIMITER = "\t\t|\t\t";

    private String code;
    private ToolType type;
    private String brand;

    @Override
    public String toString() {
        return code + DELIMITER + type + DELIMITER + brand;
    }
}
