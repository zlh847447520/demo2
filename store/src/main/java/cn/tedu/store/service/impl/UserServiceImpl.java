package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatcheException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void reg(User user) {
		// 根据参数user对象中的username属性查询用户数据
		User result = userMapper.findByUsername(user.getUsername());
		// 判断查询结果是否不为null
		if (result != null) {
			// 是：根据用户名已经查询到数据，表示用户名已经被注册，则抛出UsernameDuplicateException对象
			throw new UsernameDuplicateException("尝试注册的用户名已经被占用");
		}

		// 根据用户名没有查询到数据，表示用户名没有被注册，则允许注册
		// 补全数据：加密后的密码，盐值
		String salt = UUID.randomUUID().toString().toUpperCase();
		String password = user.getPassword(); // 获取原始密码
		String md5Password = getMd5Password(password, salt); // 执行加密
		user.setPassword(md5Password);
		user.setSalt(salt);
		// 补全数据：isDelete
		user.setIsDelete(0);
		// 补全数据：4个日志属性
		Date now = new Date();
		user.setCreatedUser(user.getUsername());
		user.setCreatedTime(now);
		user.setModifiedUser(user.getUsername());
		user.setModifiedTime(now);
		// 调用userMapper的insert()方法执行注册，获取返回的受影响行数
		Integer rows = userMapper.insert(user);
		// 判断受影响的行数是否不为1
		if (rows != 1) {
			// 是：表示注册失败，则抛出InsertException对象
			throw new InsertException("请联系客户");
		}
	}



	@Override
	public User login(String username, String password) {
		// 调用userMapper的findByUsernmae(),根据参数username 查询用户数据
		User result = userMapper.findByUsername(username);
		//判断查询结果是否为null
		if(result == null) {
			//是:登录失败,用户名没有对应数据,抛出UserNotFoundException
			throw new UserNotFoundException("登录失败,用户名错误!");
		}
		if(result.getIsDelete()==1) {
			//判断查询结果中的isDelete是否为1
			//是:用户数据标记为已删除,抛出UserNotFoundException
			throw new UserNotFoundException("用户名已被删除");
		}
		String salt=result.getSalt();
		String md5Password = getMd5Password(password, salt);
		if(!(result.getPassword().equals(md5Password))) {
			//从查询结果中取出salt值
			//调用getMd5Password(String password,String salt)方法,将参数password加密,得到md5Password
			//判断查询结果中的password与以上加密得到的md5Password是否不同
			//是:密码错误,抛出PasswordNotMatchException
			throw new PasswordNotMatcheException("登录失败,密码错误!");
		}
		User user = new User();
		//[1]将查询结果中除了uid,username,avatar以外的属性都设置为null
		user.setUid(result.getUid());
		user.setUsername(username);
		user.setAvatar(result.getAvatar());
		//[2]创建新的User对象,将查询结果中的uid,username,avatar封装到新的对象中
		//[2]返回新的User对象

		return user;
	}

	@Override
	public void changeAvatar(Integer uid, String username, String avatar) {
		User result = userMapper.findByUid(uid);
		//根据参数uid查询用户数据
		if(result.getUid()==null) {
			//判断查询结果是否为null
			//是:UserNotFoundException
			throw new UserNotFoundException("用户名不存在");
		}
		if(result.getIsDelete()==1) {
			//判断查询结果中的isDelete是否为1
			//是:UserNotFoundException
			throw new UserNotFoundException("该用户已被删除");
		}

		// 执行更新头像，并获取返回的受影响的行数
		Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
		// 判断受影响的行数是否不为1
		if (rows != 1) {
			// 是：抛出UpdateException
			throw new UpdateException("更新用户头像时出现未知错误，请联系系统管理员");
		}
	}

	@Override
	public void changePassword(Integer uid,String username,String oldPassword,String newPassword) {
		User result = userMapper.findByUid(uid);
		//根据参数uid查询用户数据
		if(result.getUid()==null) {
			//判断查询结果是否为null
			//是:UserNotFoundException
			throw new UserNotFoundException("用户名不存在");
		}
		if(result.getIsDelete()==1) {
			//判断查询结果中的isDelete是否为1
			//是:UserNotFoundException
			throw new UserNotFoundException("该用户已被删除");
		}
		String salt=result.getSalt();
		String oldMd5Password = getMd5Password(oldPassword, salt);
		if(!(result.getPassword().equals(oldMd5Password))) {
			//从查询结果中获取salt值
			//将参数oldPassword结合salt加密,得到oldMd5Password
			//判断oldMd5Password与查询结果中的密码是否不同
			//是:抛出PasswordNotMatcheException
			throw new PasswordNotMatcheException("密码错误");
		}
		String newMd5Password =getMd5Password(newPassword, salt);
		//将参数newPassword结合salt加密,得到newMd5Password
		Date modifiedTime = new Date();
		//创建当前时间对象
		Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password,username, modifiedTime);
		//执行修改密码,并获取受影响的行数
		if(rows!=1) {
			//判断受影响的行数是否不为1
			//是:抛出UpdateException
			throw new UpdateException("修改密码失败");
		}
	}
	@Override
	public void changeInfo(Integer uid, String username, User user) {
		// 根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：用户id没有对应的数据，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}
		// 判断查询结果中的isDelete是否为1
		if (result.getIsDelete() == 1) {
			// 是：用户数据标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}
		//将参数uid封装到参数user的uid属性中
		user.setUid(uid);
		//将参数username封装到参数user的modifiedUser属性中
		user.setModifiedUser(username);
		//新创建当前时间对象封装到参数user的modifiedTime属性中
		user.setModifiedTime(new Date());
		//调用userMapper对象的updateInfoByUid()执行更新,并获取受影响的行数
		Integer rows = userMapper.updateInfoByUid(user);
		//判断受影响的行数是否不为1
		if(rows!=1) {
			//是:抛出UpdateException
			throw new UpdateException("更新用户时出现未知错误");
		}
	}

	@Override
	public User getInfo(Integer uid) {
		// 根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：用户id没有对应的数据，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}

		// 判断查询结果中的isDelete是否为1
		if (result.getIsDelete() == 1) {
			// 是：用户数据标记为已删除，抛出UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}

		// 创建新的User对象
		User user = new User();
		// 向新User对象中封装username, phone, email, gender
		user.setUsername(result.getUsername());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		// 返回新User对象
		return user;
	}





	/**
	 * 执行密码加密
	 * @param password 密码
	 * @param salt 盐值
	 * @return 加密后的结果
	 */

	private String getMd5Password(String password, String salt) {
		System.err.println("执行加密：");
		System.err.println("\t密码：" + password);
		System.err.println("\t盐值：" + salt);

		// 加密规则：
		// 1. 将盐拼接在密码的前、后各1次
		// 2. 加密5次
		for (int i = 0; i < 5; i++) {
			password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
		}
		System.err.println("\t结果：" + password);
		return password;
	}




}





