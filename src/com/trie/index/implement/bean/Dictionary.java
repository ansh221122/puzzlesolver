package com.trie.index.implement.bean;


import reflection.utility.*;

public class Dictionary {

	@PrimaryKey(name="ID")
	private long id;
	@Column(name="SEARCH_QUERY")
	private String searchQuery;
	@unique
	@Column(name="RESULT_QUERY")
	private String resultQuery;
	
	
	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dictionary(String searchQuery, String resultQuery) {
		super();
		this.searchQuery = searchQuery;
		this.resultQuery = resultQuery;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	public String getResultQuery() {
		return resultQuery;
	}
	public void setResultQuery(String resultQuery) {
		this.resultQuery = resultQuery;
	}
}
