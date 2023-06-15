package io.github.bzdgn.receipe.search;

public class SearchCriteria {

	private String filterKey;
	private Object value;
	private String operation;

	public SearchCriteria() {
	}

	public SearchCriteria(String filterKey, Object value, String operation) {
		super();
		this.filterKey = filterKey;
		this.value = value;
		this.operation = operation;
	}

	public SearchCriteria(String filterKey, String operation, Object value) {
		super();
		this.filterKey = filterKey;
		this.operation = operation;
		this.value = value;
	}

	public String getFilterKey() {
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
