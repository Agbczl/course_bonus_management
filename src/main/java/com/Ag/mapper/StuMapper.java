package com.Ag.mapper;

import com.Ag.pojo.AssessmentApplication;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper {

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password
    );

    @Select("select user.username,apply_record.title,apply_record.category,apply_record.score,apply_record.status,apply_record.create_time,apply_record.description from apply_record,user " +
            "where apply_record.student_id = user.id and " +
            "user.username = #{username}")
    List<AssessmentApplicationVo> list(String username);
}
