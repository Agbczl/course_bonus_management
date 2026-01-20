package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentApplication {
    private Integer id;
    private Integer username;
    private  Integer title;
    private String description;
    private String status;
    private String auditComment;
    private LocalDateTime createTime;
}
