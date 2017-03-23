package com.rest;

import java.util.List;

public class CRequest {

	String pages;
	
	String typeOf;

	String searchQuery;

	List<String> regions;

	boolean maxDepth;

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public boolean isMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(boolean maxDepth) {
		this.maxDepth = maxDepth;
	}

	public String getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(String typeOf) {
		this.typeOf = typeOf;
	}
	
}
