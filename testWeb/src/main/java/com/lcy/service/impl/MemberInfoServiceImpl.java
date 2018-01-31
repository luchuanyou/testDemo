package com.lcy.service.impl;

import com.lcy.common.utils.exception.CustomException;
import com.lcy.mapper.MemberInfoMapper;
import com.lcy.model.MemberInfo;
import com.lcy.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoServiceImpl implements MemberInfoService{


    @Autowired
    MemberInfoMapper memberInfoMapper;

    public List<MemberInfo> queryMemberInfoAll() {
        return memberInfoMapper.queryMemberInfoAll();
    }

    public void updateMemberInfoById(Long id, MemberInfo memberInfo) throws CustomException {
        if(null == id)
            throw new CustomException("1001","参数id不能为空");

        try {
            memberInfo.setId(id);
            int num = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
            if(num <= 0){
                throw new CustomException("2001","更新会员信息失败");
            }
        }  catch (CustomException e) {
            e.printStackTrace();
            throw new CustomException(e.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("9999","更新会员信息失败");
        }
    }

    public void updateMemberInfoById(Long id, Integer status) throws CustomException {
        if(null == id)
            throw new CustomException("1001","参数id不能为空");
        if(null == status)
            throw new CustomException("1001","参数status不能为空");

        try {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(id);
            memberInfo.setStatus(status);
            int num = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
            if(num <= 0){
                throw new CustomException("2001","更新会员信息失败");
            }
        }  catch (CustomException e) {
            e.printStackTrace();
            throw new CustomException(e.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("9999","更新会员信息失败");
        }
    }
}
