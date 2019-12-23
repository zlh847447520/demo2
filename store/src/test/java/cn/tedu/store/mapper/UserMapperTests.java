package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	
	@Autowired
	private UserMapper mapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("mybatis");
		user.setPassword("1234");
		
		System.err.println(user); // id=null
		Integer rows = mapper.insert(user);
		System.err.println("rows=" + rows);
		System.err.println(user); // id=?
	}
	@Test
	 public void updatePasswordByUid() {
	  Integer uid = 1;
	  String password = "1908";
	  String modifiedUser = "spring";
	  Date modifiedTime = new Date();
	  Integer rows = mapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
	  System.err.println(rows);
	 }
	 
	 @Test
	 public void findByUid() {
	  Integer uid = 1;
	  User result = mapper.findByUid(uid);
	  System.err.println(result);
	 }
	@Test
	public void findByUsername() {
		String username = "spring1";
		User result = mapper.findByUsername(username);
		System.err.println(result);
	}
	@Test
	public void updateInfoByUid() {
		User user = new User();
		user.setUid(10);
		user.setPhone("1008666");
		user.setEmail("847447555@qq.com");
		user.setGender(1);
		user.setModifiedUser("abc6");
		user.setModifiedTime(new Date());
		Integer rows = mapper.updateInfoByUid(user);
		System.out.println("rows="+rows);
	}
	@Test
	public void updateAvatarByUid() {
	    Integer uid = 10;
	    String avatar = "abc6";
	    String modifiedUser = "abc6";
	    Date modifiedTime = new Date();
	    Integer rows = mapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
	    System.err.println("rows=" + rows);
	}
}






