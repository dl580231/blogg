package com.nuc.a4q.aspect;

import org.junit.jupiter.api.Test;

/*import org.aspectj.lang.annotation.Aspect;
import org.junit.After;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopDemo {
	@After("execution(public * com.nuc.a4q.service.serviceImpl.PersonInfoServiceImpl.loginAuth(..))")
	public void beforeDemo() {
		System.out.println("aop-before");
	}
}
*/

public class AopDemo{
	@Test
	public void demo() {
		String str = "";
        long start = System.currentTimeMillis();
        for(int i=0; i<100000; i++)
            str += "a";
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        StringBuilder sb = new StringBuilder();
        start = System.currentTimeMillis();
        for(int i=0; i<100000; i++)
            sb = new StringBuilder().append(sb).append("a");//为了保证公平，这里也要new
        str = sb.toString();
        end = System.currentTimeMillis();   
        System.out.println(end - start);
	}
}