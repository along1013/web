package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseValueInfo<M extends BaseValueInfo<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setNewValue(java.lang.String newValue) {
		set("new_value", newValue);
	}

	public java.lang.String getNewValue() {
		return get("new_value");
	}

	public void setWeek(java.lang.String week) {
		set("week", week);
	}

	public java.lang.String getWeek() {
		return get("week");
	}

	public void setYear(java.lang.String year) {
		set("year", year);
	}

	public java.lang.String getYear() {
		return get("year");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}

	public java.lang.Integer getSort() {
		return get("sort");
	}

	public void setValueTime(java.util.Date valueTime) {
		set("value_time", valueTime);
	}

	public java.util.Date getValueTime() {
		return get("value_time");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

}
