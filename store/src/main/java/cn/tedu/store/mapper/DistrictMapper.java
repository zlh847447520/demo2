package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;

/**
 * 处理省/市/区数据的持久层
 * @author asus
 *
 */
public interface DistrictMapper { 
	/**
	 * 获取全国所有的省/某省所有的市/某市所有的区的数据列表
	 * @param parent父级单位的行踪代号,如果获取全国所有的
	 * @return
	 */
	List<District> findByParent(String parent);
	/**
	 * 根据省/市/区的代号获取对应的名称
	 * @param code
	 * @return
	 */
	String findNameByCode(String code);
}
