package com.lcy.mapper;

import com.lcy.model.MemberInfo;

import java.util.List;

public interface MemberInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberInfo record);

    List<MemberInfo> queryMemberInfoAll();
}