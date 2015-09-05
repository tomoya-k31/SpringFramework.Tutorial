package com.pt.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by usr0200379 on 15/08/07.
 */
@Controller
public class SampleController {

    @RequestMapping("/simple")
    public @ResponseBody
    String simple() {
        return "Hello world!";
    }

}
