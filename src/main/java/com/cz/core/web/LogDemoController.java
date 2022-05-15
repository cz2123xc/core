package com.cz.core.web;

import com.cz.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // final 붙은 생성자를 자동으로 생성해준다. (의존성 주입)
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger; // MyLogger 스코프가 request 라서 생성시에 없다고 오류가 나는 중.
//    private final ObjectProvider<MyLogger> myLoggerProvider; // (방법1) 이렇게 해줘야 스코프가 request 라서 생성시에 없다고 오류가 나지 않는다.

    // (방법2) 이렇게 해주면 proxyMode = ScopedProxyMode.TARGET_CLASS 때문에 생성시에 없다고 오류가 나지 않는다.
    private final MyLogger myLogger;
    // (방법2) 주의점은 싱글톤 처럼 쓸 수 있지만 리퀘스트 요청마다 각각의 객체가 생성이 되므로 주의 해야 한다.
    // (방법2) 이런 특별한 스코프는 꼭 필요한 곳에서 최소화 해서 사용하자. 유지/보수가 어려워 질 수 있다.


    @RequestMapping("log-demo")
    @ResponseBody // html 이 아니라 json 을 리턴해준다.
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        // 새로 생성된 스프링이 만든 가짜 MyLogger 객체를 찍어보기 -> 프로바이더 처럼 동작한다
        System.out.println("myLogger = " + myLogger.getClass()); // (방법3)


//        MyLogger myLogger = myLoggerProvider.getObject(); // (방법1) 이 시점이 실행되면서 MyLogger 인스턴스를 생성해주고 이걸 계속 사용할 수 있다.
        myLogger.setRequestURL(requestURL); // 방법3 에서는 이 시점에서 가짜 MyLogger 객체를 진짜 객체로 바꿔주고 이걸 계속 사용할 수 있다.

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }


}
