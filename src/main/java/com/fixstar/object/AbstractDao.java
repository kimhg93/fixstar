package com.fixstar.object;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 공통 사항 작성
 * 모든 Dao 에서 상속받음
 */
public abstract class AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    @Autowired
    @Qualifier("sqlSessionTemplate")
    protected SqlSessionTemplate sqlSession;

    private <P> void logger(String queryId, P params){
        logger.info("[QUERY ID] : " + queryId + " & [PARAMS] : " + params.toString());
    }

    public <T, P> T selectOne(String queryId, P params){
        logger(queryId, params);
        return (T) sqlSession.selectOne(queryId, params);
    }
    public <T, P> List<T> selectList(String queryId, P params){;
        logger(queryId, params);
        return (List<T>) sqlSession.selectList(queryId, params);
    }
    public <P> Integer insert(String queryId, P params){
        logger(queryId, params);
        return sqlSession.insert(queryId, params);
    }
    public <P> Integer insert(String queryId, List<P> params){
        logger(queryId, params);
        return sqlSession.insert(queryId, params);
    }
    public <P> Integer update(String queryId, P params){
        logger(queryId, params);
        return sqlSession.update(queryId, params);
    }
    public <P> Integer update(String queryId, List<P> params){
        logger(queryId, params);
        return sqlSession.update(queryId, params);
    }
    public <P> Integer delete(String queryId, P params){
        logger(queryId, params);
        return sqlSession.delete(queryId, params);
    }
    public <P> Integer delete(String queryId, List<P> params){
        logger(queryId, params);
        return sqlSession.delete(queryId, params);
    }
}
