package com.ralap.blog;

import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCoreApplicationTests {

    @Autowired
    private BizArticleMapper bizArticleMapper;

    @Test
    public void contextLoads() {
        List<BizArticle> articleList = bizArticleMapper.selectAll();

    }

}
