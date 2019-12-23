package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址数据的业务层接口
 */
public interface IAddressService {

	/**
	 * 增加收货地址数据
	 * @param uid 用户id
	 * @param username 用户名
	 * @param address 新的收货地址数据
	 */
	void addnew(Integer uid, String username, Address address);
	
	/**
	 * 获取某用户的收货地址列表
	 * @param uid 用户的id
	 * @return 该用户的收货地址列表
	 */
	List<Address> getByUid(Integer uid);
	
	/**
	 * 将某收货地址设置为默认
	 * @param aid 收货地址数据的id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 */
	void setDefault(Integer aid, Integer uid, String username);
	
	/**
	 * 删除收货地址
	 * @param aid 收货地址数据的id
	 * @param uid 当前登录的用户id
	 * @param username 当前登录的用户名
	 */
	void delete(Integer aid, Integer uid, String username);
	
}





