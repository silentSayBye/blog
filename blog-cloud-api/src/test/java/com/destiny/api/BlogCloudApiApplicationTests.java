package com.destiny.api;


import com.alibaba.fastjson.JSONObject;
import com.destiny.api.controller.ArticleController;
import com.destiny.api.domain.vo.ArticleVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
public class BlogCloudApiApplicationTests {

    @Autowired
    private ArticleController  articleController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .build();
    }



    @Test
    public void contextLoads() {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setContent("张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的张岱发的饭卡的");
        articleVO.setArticleId(111);
        articleVO.setPublishType(1);
        articleVO.setSystemClass(2);
        articleVO.setTitle("测试");
        articleVO.setType(3);
        articleVO.setStatus(1);
        String jsonStr = JSONObject.toJSONString(articleVO);

        try {
            this.mockMvc.perform(post("/article").accept(MediaType.APPLICATION_JSON).content(jsonStr))
                    .andExpect(content().json("{\"code\":\"1\",\"message\":\"Success\",\"data\":\"Article save successfully.\"}"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
