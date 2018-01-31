package com.lcy.controller;

import com.lcy.common.utils.exception.CustomException;
import com.lcy.common.utils.http.HttpClientUtil;
import com.lcy.model.MemberInfo;
import com.lcy.service.MemberInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/one")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    MemberInfoService memberInfoService;

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test(String data){
        System.out.println("请求参数,request params:"+data);
        logger.info("请求参数,request params:"+data);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",data);
        logger.error("返回结果,map:"+map);
        return map;
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<MemberInfo> query(){
        List<MemberInfo> list = memberInfoService.queryMemberInfoAll();
        System.out.println("list:"+list);
        for (MemberInfo memberInfo:list) {
            try {
                String url = "http://llfinace.dev2.001bank.com/llfinace/";//开发
//                String url = "http://192.168.200.109:8083/llfinace/";//生产
                Map<String,String> map = new HashMap<String, String>();
                map.put("borrowAssetId",memberInfo.getAge()+"");
                String result = HttpClientUtil.doPost(url+"/v1/api/borrowzt/signAgreementAsset", map);
                System.out.println("入参url:"+url+",map:"+map);
                System.out.println("============borrowAssetId:"+memberInfo.getAge()+",result:"+result);
                memberInfoService.updateMemberInfoById(memberInfo.getId(),2);
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }


}
