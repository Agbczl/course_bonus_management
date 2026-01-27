package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer score;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<String> imageList;
    private String imageListStr;
}
