package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileIOException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileStateException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("reg")
	public JsonResult<Void> reg(User user) {
		// 调用userService的reg()方法实现注册
		userService.reg(user);
		// 返回
		return new JsonResult<Void>(OK);
	}
	
	@RequestMapping("login")
	public JsonResult<User> login(
			String username, String password,
			HttpSession session) {
		// 调用userService的login()方法实现登录
		User user = userService.login(username, password);
		// 将uid、username存入到Session中
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		System.err.println("用户登录成功：");
		System.err.println("\tsession.uid=" + user.getUid());
		System.err.println("\tsession.username=" + user.getUsername());
		// 返回
		return new JsonResult<>(OK, user);
	}

	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(
		String oldPassword, String newPassword, 
		HttpSession session) {
		// 从Session中获取uid和username
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		// 调用userService的方法实现功能
		userService.changePassword(uid, username, oldPassword, newPassword);
		// 返回
		return new JsonResult<>(OK);
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(
			User user, HttpSession session) {
		// 从Session中获取uid和username
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		// 调用userService的方法实现功能
		userService.changeInfo(uid, username, user);
		// 返回
		return new JsonResult<>(OK);
	}
	
	@GetMapping("info")
	public JsonResult<User> getInfo(HttpSession session) {
		// 从Session中获取uid
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		// 调用userService的方法实现功能
		User user = userService.getInfo(uid);
		// 返回
		return new JsonResult<>(OK, user);
	}
	
	// 上传文件大小的上限值
	@Value("${project.upload.avatar-max-size}")
	private int avatarMaxSize;
	// 允许上传的文件类型
	private static final List<String> AVATAR_TYPES = new ArrayList<>();
	
	static {
		AVATAR_TYPES.add("image/jpeg");
		AVATAR_TYPES.add("image/png");
	}
	
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(
			@RequestParam("file") MultipartFile file, 
			HttpSession session) {
		// 检查上传的文件是否为空
		if (file.isEmpty()) {
			throw new FileEmptyException("请选择您要上传的头像文件");
		}
		
		// 检查上传的文件大小
		if (file.getSize() > avatarMaxSize) {
			throw new FileSizeException(
				"不允许上传超过" + avatarMaxSize / 1024 + "KB的文件");
		}
		
		// 检查上传的文件类型：image/jpeg, image/png
		if (!AVATAR_TYPES.contains(file.getContentType())) {
			throw new FileTypeException("您上传的头像类型超出了限制，允许上传的文件类型有：" + AVATAR_TYPES);
		}
		
		// 获取原始文件名
		String originalFilename = file.getOriginalFilename();
		
		// 保存上传的头像的文件夹
		String dir = session.getServletContext().getRealPath("upload");
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		// 保存上传的头像时使用的文件名
		String name = System.currentTimeMillis() + "-" + System.nanoTime();
		String suffix = "";
		int i = originalFilename.lastIndexOf(".");
		if (i > 0) {
			suffix = originalFilename.substring(i);
		}
		String filename = name + suffix;
		
		// 用户头像的路径(响应给客户端的，且存入到数据库的)
		String avatar = "/upload/" + filename;
		System.out.println("avatar=" + avatar);
		
		try {
			// 执行保存
			File dest = new File(dir, filename);
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileStateException(
				"文件状态异常，可能原文件已经无法访问，请重新选择头像文件并再次上传");
		} catch (IOException e) {
			throw new FileIOException(
				"读写文件时出现异常，请重新上传");
		}
		
		// 从Session中获取uid和username
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		// 更新用户的头像
		userService.changeAvatar(uid, username, avatar);
		
		// 返回
		return new JsonResult<>(OK, avatar);
	}
	
}











