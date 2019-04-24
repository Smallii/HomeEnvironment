package com.home.HomeEnvironment;

import com.home.HomeEnvironment.entity.SysUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HomeEnvironmentApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(HomeEnvironmentApplication.class, args);
		} catch (Exception e) {
			System.err.println("启动有异常："+ e.getMessage());
		}
		System.out.println("启动成功");
	}

}

