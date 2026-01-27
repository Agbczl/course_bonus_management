package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class imageList {
    private Long id;
    private Long applyId;
    private String imagePath;
    private LocalDateTime createTime;
}

