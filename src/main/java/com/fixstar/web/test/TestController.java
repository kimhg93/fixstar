package com.fixstar.web.test;

import com.fixstar.object.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class TestController extends AbstractController {

    private final TestService testService;

    @GetMapping(value="/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        System.err.println("##################  " + testService.selectTest());
        mv.setViewName("views/test");

        // async function
        // admin index
        // -main image upload
        // -check word
        // board

        return mv;
    }
}
