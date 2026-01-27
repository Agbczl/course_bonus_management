package com.Ag.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyMaterialMapper {

    void insertBatch(@Param("applyId") Long applyId,
                     @Param("paths") List<String> paths);

    List<String> selectByApplyId(Long applyId);

    void deleteByApplyId(List<Long> ids);

}

