package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车数据的业务层接口
 */
public interface ICartService {
	
	/**
	 * 将商品添加到购物车
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户名
	 * @param pid 商品的id
	 * @param amount 添加到购物车中的数量
	 */
	void addToCart(Integer uid,String username,Integer pid,Integer amount);
	
	/**
	 * 查询某用户的购物车数据
	 * @param uid 用户的id
	 * @return 该用户的购物车数据列表
	 */
	List<CartVO> getVOByUid(Integer uid);

	/**
	 * 将购物车中的商品的数量加1
	 * @param cid 购物车数据的id
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户
	 * @return 购物车中的商品的新的数量
	 */
	Integer addNum(Integer cid,Integer uid,String username);
	
	/**
	 * 根据多个购物车数据id查询多条购物车数据
	 * @param cids 多个购物车数据id
	 * @return 匹配的购物车数据的集合
	 */
	List<CartVO> getVOByCids(Integer[] cids,Integer uid);
}
