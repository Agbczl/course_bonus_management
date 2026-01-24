package com.Ag.mapper;

import com.Ag.pojo.ApplicationForm;
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
    List<AssessmentApplicationVo> SelectPersonalApply(String username,String category,String title);

    void DeleteAssessmentApplication(long Stu_id,long id );

    void InsertApplication(@Param("stuid") long Stu_id, @Param("a") ApplicationForm assessmentApplicationVo);

    void UpdateApplication(@Param("a") ApplicationForm applicationForm);
}
