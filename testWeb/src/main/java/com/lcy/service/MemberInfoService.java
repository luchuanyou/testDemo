package com.lcy.service;

import com.lcy.common.utils.exception.CustomException;
import com.lcy.model.MemberInfo;

import java.util.List;

public interface MemberInfoService {

    /**
     * 查询会员信息
     * @return
     */
    public List<MemberInfo> queryMemberInfoAll();

    /**
     * 更新会员信息
     * @param id
     * @param memberInfo
     * @throws CustomException
     */
    public void updateMemberInfoById(Long id,MemberInfo memberInfo) throws CustomException;

    /**
     * 更新会员信息
     * @param id
     * @param status
     * @throws CustomException
     */
    public void updateMemberInfoById(Long id,Integer status) throws CustomException;
}
