package com.fixstar.web.test;

import com.fixstar.object.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao extends AbstractDao {

    public String selectTest() {
        return sqlSession.selectOne("testMapper.selectTest");
    }
}
