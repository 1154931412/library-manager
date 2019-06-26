package com.library.mapper;

import com.library.pojo.LendInfo;
import com.library.pojo.LendInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LendInfoMapper {
    int countByExample(LendInfoExample example);

    int deleteByExample(LendInfoExample example);

    int deleteByPrimaryKey(Long sernum);

    int insert(LendInfo record);

    int insertSelective(LendInfo record);

    List<LendInfo> selectByExample(LendInfoExample example);

    LendInfo selectByPrimaryKey(Long sernum);

    int updateByExampleSelective(@Param("record") LendInfo record, @Param("example") LendInfoExample example);

    int updateByExample(@Param("record") LendInfo record, @Param("example") LendInfoExample example);

    int updateByPrimaryKeySelective(LendInfo record);

    int updateByPrimaryKey(LendInfo record);
}