package com.yigang.entity;

import java.util.List;

public class Menu {

	private Long menuId;

    private String name;

    private String icon;

    private String href;

    private String perms;

    private String spread;

    private Long parentId;

    private Long sorting;
    
    private String checked;
    
    private Boolean isOpen=false;
    
    private List<Menu> children;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSorting() {
		return sorting;
	}

	public void setSorting(Long sorting) {
		this.sorting = sorting;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", name=" + name + ", icon=" + icon + ", href=" + href + ", perms=" + perms
				+ ", spread=" + spread + ", parentId=" + parentId + ", sorting=" + sorting + ", checked=" + checked
				+ ", isOpen=" + isOpen + ", children=" + children + "]";
	}
    
}
