package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentApplicationVo {
    private Long id;
    private String username;
    private Long student_id;
    private String major;
    private String grade;
    private String title;
    private String description;
    private String category;
    private BigDecimal score;
    private String status;
    private String teacherComment;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<String> imageList;
    private String imageListStr;
}
