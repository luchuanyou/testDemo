package com.lcy.test;

import com.lcy.common.utils.http.HttpClientUtil;
import com.lcy.model.MemberInfo;
import com.lcy.service.MemberInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDemo {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberInfoService memberInfoService;

    @Test
    public void testDD(){
        List<MemberInfo> list = memberInfoService.queryMemberInfoAll();
        System.out.println(list);
        for (int i = 0; i < 1; i++) {
            logger.info(i+"结果集,list:{}",list.size());
            logger.debug(i+"结果集,list:"+list);
        }

    }

    public static void main(String[] args) {

        String str = "abc";
        System.out.println(str.indexOf(98,0));
        System.out.println((int)'A');
        System.out.println((char) 36+","+(char)46);

        String url = "";//"http://llfinace.dev2.001bank.com/llfinace/";
        Map<String,String> map = new HashMap<String, String>();
        map.put("borrowAssetId","6");
        String result = HttpClientUtil.doPost(url+"/v1/api/borrowzt/signAgreementAsset", map);
        System.out.println("============result:"+result);
    }
}
