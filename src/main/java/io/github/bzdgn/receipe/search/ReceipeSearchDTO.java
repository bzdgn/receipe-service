package io.github.bzdgn.receipe.search;

import java.util.List;

public class ReceipeSearchDTO {

	private List<SearchCriteria> searchCriteriaList;

	public ReceipeSearchDTO() {
	}

	public ReceipeSearchDTO(List<SearchCriteria> searchCriteriaList, String dataOption) {
		super();
		this.searchCriteriaList = searchCriteriaList;
	}

	public List<SearchCriteria> getSearchCriteriaList() {
		return searchCriteriaList;
	}

	public void setSearchCriteriaList(List<SearchCriteria> searchCriteriaList) {
		this.searchCriteriaList = searchCriteriaList;
	}

}
