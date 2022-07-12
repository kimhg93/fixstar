package com.fixstar.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 공통 사항 작성
 * 모든 controller 에서 상속받음
 */
public abstract class AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    public void logger(String type, String msg){
        logger.info(msg);
    }
}
