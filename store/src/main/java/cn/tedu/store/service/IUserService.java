package cn.tedu.store.service;

import cn.tedu.store.entity.User;
/**
 * 处理用户相关业务的接口
 * @author asus
 *
 */
public interface IUserService {
	/**
	 * 用户信息
	 * @param user 新用户信息
	 */
	void reg(User user);
	User login(String username,String password);
	
	/**
	 * 
	 * @param uid 用户名id
	 * @param username 用户名
	 * @param oldPassword 用户旧密码
	 * @param newPassword 用户修改新密码
	 */
	void changePassword(Integer uid,String username,String oldPassword,String newPassword);
	
	/**
	 * 修改头像
	 * @param uid
	 * @param username
	 * @param avater 新头像
	 */
	void changeAvatar(Integer uid,String username,String avater);

	/**
	 * 获取当前用户的用户资料
	 * @param uid 当前登录的用户的id
	 * @return 该用户的资料
	 */
	User getInfo(Integer uid);
	/**
	 * 修改用户资料
	 * @param uid
	 * @param username 
	 * @param user用户的新资料,可以包含手机
	 */
	void changeInfo(Integer uid,String username,User user);
	
}
