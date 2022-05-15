package com.cz.core.web;

import com.cz.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerProvider; // (방법1) ObjectProvider 자체는 단순히 오브젝트를 찾아주기 때문에 이렇게 선언해서 사용해도 무방하다(새로운 객체를 반환하지 않음)
    private final MyLogger myLogger; // (방법2) 이렇게 선언해서 사용하면 새로운 객체를 반환해서 사용하기 때문에 반드시 새로운 객체를 생성해야 한다.

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
