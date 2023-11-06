package com.shinhan.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shinhan.dto.DeptVO;
import com.shinhan.dto.EmpVO;
import com.shinhan.model.DeptService;
import com.shinhan.model.EmpService;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext ctx = new ClassPathXmlApplicationContext("project4.xml");
        DeptService dService = (DeptService)ctx.getBean("dService");
        //DeptVO dept = new DeptVO(10,"부서이름변경",100,1800);
        //dService.updateDept(dept);
        DeptVO dept = dService.selectById(10);
        System.out.println("App App App App"+dept);
        System.out.println("end App------------------------------------------------------------");
//        EmpService eService = ctx.getBean("eService", EmpService.class);
//        EmpVO emp = eService.selectById(100);
//        System.out.println(emp);
    }
}
