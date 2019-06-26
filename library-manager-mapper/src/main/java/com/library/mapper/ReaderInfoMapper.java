package com.library.mapper;

import com.library.pojo.ReaderInfo;
import com.library.pojo.ReaderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReaderInfoMapper {
    int countByExample(ReaderInfoExample example);

    int deleteByExample(ReaderInfoExample example);

    int deleteByPrimaryKey(Integer readId);

    int insert(ReaderInfo record);

    int insertSelective(ReaderInfo record);

    List<ReaderInfo> selectByExample(ReaderInfoExample example);

    ReaderInfo selectByPrimaryKey(Integer readId);

    int updateByExampleSelective(@Param("record") ReaderInfo record, @Param("example") ReaderInfoExample example);

    int updateByExample(@Param("record") ReaderInfo record, @Param("example") ReaderInfoExample example);

    int updateByPrimaryKeySelective(ReaderInfo record);

    int updateByPrimaryKey(ReaderInfo record);
}