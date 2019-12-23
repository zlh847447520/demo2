package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.controller.ex.ProductNotFoundException;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;

/**
 * 处理商品数据的业务层实现类
 */
@Service
public class ProductSericeImpl implements IProductService {
	
	@Autowired
	private ProductMapper productMapper;

	/**
	 * 获取“新到好货”列表
	 * @return “新到好货”列表
	 */
	private List<Product> findNewList() {
		return productMapper.findNewList();
	}
	private Product findById(Integer id) {
		return productMapper.findById(id);
	}
	@Override
	public List<Product> getNewList() {
		List<Product> list = findNewList();
		for (Product product : list) {
			product.setStatus(null);
			product.setPriority(null);
			product.setCreatedUser(null);
			product.setCreatedTime(null);
			product.setModifiedUser(null);
			product.setModifiedTime(null);
		}
		return list;
	}
	
	@Override
	public Product getById(Integer id) {
		Product result = findById(id);
		if(result==null) {
			throw new ProductNotFoundException("尝试访问的商品数据不存在");
		}
		result.setStatus(null);
		result.setPriority(null);
		result.setCreatedUser(null);
		result.setCreatedTime(null);
		result.setModifiedUser(null);
		result.setModifiedTime(null);
		return result;
	}
	

}








