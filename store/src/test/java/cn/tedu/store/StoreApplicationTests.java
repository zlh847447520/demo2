package cn.tedu.store;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void contextLoads() throws SQLException {
		System.err.println(dataSource.getConnection());
	}

	@Test
	public void md5() {
		String password="1";
		String md5Password=DigestUtils.md5DigestAsHex(password.getBytes());
		System.err.println(md5Password);
		
	}
}