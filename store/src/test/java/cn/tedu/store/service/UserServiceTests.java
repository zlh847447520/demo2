package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private IUserService service;
	
	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("abc6");
			user.setPassword("12345");
			service.reg(user);
			System.err.println("ok.");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
		}
		
	}
	@Test
	public void login() {
		try {
		String username="abc6";
		String password="12345";
		User user = service.login(username, password);
		System.out.println(user);
		}catch(ServiceException e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void changePassword() {
		try {
			Integer uid= 10;
			String username="abc6";
			String oldPassword="1234";
			String newPassword="8888";
			service.changePassword(uid, username, oldPassword, newPassword);
		} catch (ServiceException e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void changeAvatar() {
		try {
			Integer uid = 10;
			String username = "系统管理员";
			String avatar = "9999";
			service.changeAvatar(uid, username, avatar);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void changeInfo() {
		try {
			Integer uid= 10;
			String username="abc6";
			User user = new User();
			user.setEmail("111111@qq.com");
			user.setPhone("123123456");
			user.setGender(1);
			service.changeInfo(uid, username, user);
		} catch (ServiceException e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void getInfo() {
		try {
			Integer uid = 18;
			User result = service.getInfo(uid);
			System.err.println(result);
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	
}
