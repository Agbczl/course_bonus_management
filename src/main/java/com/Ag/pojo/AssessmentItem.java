package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentItem {
    private Integer id; //主键
    private String itemName;
    private short maxScore;
    private String description;
}
