package com.lzc.shop.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.lzc.shop.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体层
 * 
 * @author lzc
 * 
 */
public class Category {
	private Integer cid;
	private String cname;
	// 配置二级分类的关联
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
}
