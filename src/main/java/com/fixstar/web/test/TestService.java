package com.fixstar.web.test;

import com.fixstar.object.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService extends AbstractService {

    private final TestDao testDao;

    public String selectTest() {
        return testDao.selectTest();
    }
}
