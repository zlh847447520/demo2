package cn.tedu.store.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用于封装返回给客户端的数据的类
 * 
 * @author asus
 *
 * @param <T> 响应数据的类型
 */
@JsonInclude(Include.NON_NULL)
public class JsonResult<T>{
	
	@JsonInclude(Include.ALWAYS)
	private Integer state;
	private String message;
	private T data;
	
	public JsonResult() {
		super();
	}
	
	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	public JsonResult(Throwable e) {
		super();
		this.message = e.getMessage();
	}
	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
