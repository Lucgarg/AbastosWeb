package com.abastos.market.web.model;

public class Pagination {
	private int totalPages;
	private int firstPagedPage;
	private int lastPagedPage;
	private int page;
	private int firstPage;
	public Pagination() {
	
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getFirstPagedPage() {
		return firstPagedPage;
	}
	public int getLastPagedPage() {
		return lastPagedPage;
	}
	public int getPage() {
		return page;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public void setFirstPagedPage(int firstPagedPage) {
		this.firstPagedPage = firstPagedPage;
	}
	public void setLastPagedPage(int lastPagedPage) {
		this.lastPagedPage = lastPagedPage;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

}
