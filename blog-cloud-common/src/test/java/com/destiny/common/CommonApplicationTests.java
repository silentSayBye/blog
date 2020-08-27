package com.destiny.common;

import com.destiny.common.cache.DictCacheService;
import com.destiny.common.dao.DictRepository;
import com.destiny.common.domain.pojo.Dict;
import com.destiny.common.service.DictService;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonApplicationTests {

    @Autowired
    private DictCacheService dictCacheService;

    @Autowired
    private DictService dictService;

    @Autowired
    private DictRepository dictRepository;


    @Test
    public void DeleteAllDict() {
        dictRepository.deleteAll();
    }

	@Test
    public void insertDict() {
//		Dict dict = new Dict();
//		dict.setCode("code");
//		dict.setCategoryCode("categoryCode");
//		dictService.insertDict(dict);
//		Dict dict1 = new Dict();
//		dict1.setCode("code1");
//		dict1.setCategoryCode("categoryCode");
//		dictService.insertDict(dict1);
        Dict dict2 = new Dict();
        dict2.setCode("code2");
        dict2.setCategoryCode("destiny");
        dictService.insertDict(dict2);
    }

    @Test
    public void updateDictByIdTest() {
        Dict dict = dictService.getDictByCategoryCodeAndCode("categoryCode", "code1");
        dictService.updateDict(dict.getId());
    }

    @Test
    public void dictListCacheEqualsNullTest() {
        List<Dict> dictList = dictCacheService.findDictListByCategory("test");
        Assert.assertSame(Lists.newArrayList(), dictList);
    }

    @Test
    public void dictListCacheEqualsNotNullTest() {
        List<Dict> dictList = dictCacheService.findDictListByCategory("destiny");
        Assert.assertNotNull(dictList);
        dictList.forEach(dict -> {
            System.out.println(dict);
        });
    }

    @Test
    public void dictCacheTest() {
        Dict dictList = dictCacheService.findDictByCategoryCodeAndCode("categoryCode", "code");
    }

}
