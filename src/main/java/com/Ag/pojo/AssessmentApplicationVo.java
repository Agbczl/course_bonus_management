package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentApplicationVo {
    private Integer id;
    private String studentName;
    private String major;
    private String grade;
    private String title;
    private String description;
    private String category;
    private Integer score;
    private String status;
    private String auditComment;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
