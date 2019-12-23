package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Product;

/**
 * 处理商品数据的业务层接口
 */
public interface IProductService {

	/**
	 * 获取“新到好货”列表
	 * @return “新到好货”列表
	 */
	List<Product> getNewList();

	Product getById(Integer id);
	
}





