package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;
/**
 * 处理省业务层接口
 */
public interface IDistrictService {
	
	List<District> getByParent(String parent);
	
	String getNameByCode(String code);
}
