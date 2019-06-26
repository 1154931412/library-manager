package com.library.mapper;

import com.library.pojo.ReadCard;
import com.library.pojo.ReadCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReadCardMapper {
    int countByExample(ReadCardExample example);

    int deleteByExample(ReadCardExample example);

    int deleteByPrimaryKey(Integer readerId);

    int insert(ReadCard record);

    int insertSelective(ReadCard record);

    List<ReadCard> selectByExample(ReadCardExample example);

    ReadCard selectByPrimaryKey(Integer readerId);

    int updateByExampleSelective(@Param("record") ReadCard record, @Param("example") ReadCardExample example);

    int updateByExample(@Param("record") ReadCard record, @Param("example") ReadCardExample example);

    int updateByPrimaryKeySelective(ReadCard record);

    int updateByPrimaryKey(ReadCard record);
}