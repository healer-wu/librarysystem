package com.wky.service;

import com.wky.entity.Menu;

import java.util.List;
import java.util.Map;



public interface MenuService {
	
	public Integer update(Menu menu);
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Menu> list(Map<String, Object> map, Integer page, Integer pageSize);
	
	public Long getTotal(Map<String, Object> map);
	
	
	
}
