package com.Ag.mapper;

import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuMapper {

    @Select("select * from apply_record where id = #{id}")
     AssessmentApplicationVo selectid(Long id);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password
    );
    List<AssessmentApplicationVo> SelectPersonalApply(String username,String category,String title);

    void DeleteAssessmentApplication(long Stu_id,List<Long> ids );

    void InsertApplication(@Param("stuid") long Stu_id, @Param("a") ApplicationForm assessmentApplicationVo);

    void UpdateApplication(@Param("a") ApplicationForm applicationForm);

    @Select("select * from apply_record where category = #{category}")
    List<AssessmentApplicationVo> selectByCategory(String category);

    List<AssessmentApplicationVo> selectAll(String username, String status);

    @Update("update  apply_record set status = #{status} where id = #{id}")
    int updateById(ApplicationForm updateForm);
}

