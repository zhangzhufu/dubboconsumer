package com.alibaba.dubbo.demo.pp;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.unj.dubbotest.model.TRole;
import com.unj.dubbotest.service.DemoService;
import com.unj.dubbotest.service.RoleService;
import com.unj.dubbotest.service.impl.User;

public class Consumer {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService");
		String hello = demoService.sayHello("guoxue");
		System.out.println(hello);

		List<User> list = demoService.getUsers();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
//		//回声测试,用于检测服务是否可用
		EchoService echoService = (EchoService) context.getBean("roleService");
		// 回声测试可用性
		String status = (String) echoService.$echo("OK");
		System.out.println(status);
		assert(status.equals("OK"));
		RoleService roleService = (RoleService) context.getBean("roleService");
		List<TRole> lists = roleService.getRoles();
		if (lists != null && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				System.out.println(lists.get(i).getId());
				System.out.println(lists.get(i).getRolename());
			}
		}
		
		System.in.read();
	}

}