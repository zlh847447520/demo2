package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {

	@Autowired
	private AddressMapper mapper;
	
	@Test
	public void insert() {
		Address address = new Address();
		address.setUid(1);
		address.setName("店小二");
		Integer rows = mapper.insert(address);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void deleteByAid() {
		Integer aid = 31;
		Integer rows = mapper.deleteByAid(aid);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateDefaultByAid() {
		Integer aid = 31;
		String modifiedUser = "root";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateNonDefaultByUid() {
		Integer uid = 18;
		Integer rows = mapper.updateNonDefaultByUid(uid);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void countByUid() {
		Integer uid = 2;
		Integer count = mapper.countByUid(uid);
		System.err.println("count=" + count);
	}
	
	@Test
	public void findByAid() {
		Integer aid = 32;
		Address result = mapper.findByAid(aid);
		System.err.println(result);
	}

	@Test
	public void findLastModified() {
		Integer uid = 18;
		Address result = mapper.findLastModified(uid);
		System.err.println(result);
	}
	
	@Test
	public void findByUid() {
		Integer uid = 18;
		List<Address> list = mapper.findByUid(uid);
		System.err.println("count=" + list.size());
		for (Address item : list) {
			System.err.println(item);
		}
	}
	
}






