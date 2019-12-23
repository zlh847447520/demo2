package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	
	@Autowired
	private ICartService service;
	
	@Test
	public void addToCart() {
		try {
			
			Integer uid = 1;
			String username = "ROOT";
			Integer pid =  10000002;
			Integer amount = 3;
			service.addToCart(uid, username, pid, amount);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getVOByUid() {
		Integer uid=18;
		List<CartVO> list=service.getVOByUid(uid);
		System.err.println("count="+list.size());
		for (CartVO item : list) {
			System.err.println(item);
		}
	}
	@Test
	public void addNum() {
		try {
			Integer cid=7;
			Integer uid = 10;
			String username = "ROOT";
			Integer newNum = service.addNum(cid, uid, username);
			System.err.println("OK. newNum"+newNum);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getSimpleName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void getVOByCids() {
		Integer[] cids= {1,2,3,4,5,6,7,8,9};
		Integer uid =10;
		List<CartVO> list=service.getVOByCids(cids,uid);
		System.err.println("count="+list.size());
		for (CartVO item : list) {
			System.err.println(item);
		}
	}
}
