package com.warape.messagecenter.controller;

import com.warape.messagecenter.tasks.MessageTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanmingyu
 */
@RestController
public class TestController {

    @Autowired
    private MessageTask messageTask;

    @RequestMapping("/testSend")
    public void testSend(){
        messageTask.beSendToMq();
    }
}
