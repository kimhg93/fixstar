package com.fixstar.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 공통 사항 작성
 * 모든 service 에서 상속받음
 */
public abstract class AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    public void logger(String msg){
        logger.info(msg);
    }
}
