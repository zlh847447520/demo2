package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Product;

/**
 * 处理商品数据的持久层接口
 */
public interface ProductMapper {

	/**
	 * 获取“新到好货”列表
	 * @return “新到好货”列表
	 */
	List<Product> findNewList();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Product findById(Integer id);
	
}





