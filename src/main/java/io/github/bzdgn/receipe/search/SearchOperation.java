package io.github.bzdgn.receipe.search;

public enum SearchOperation {

	CONTAINS("contains"),
	NOT_CONTAINS("not_contains"),
	EQUALS("=="),
	NOT_EQUALS("!="),
	GREATER_THAN(">"),
	GREATER_THAN_EQUALS(">="),
	LESS_THAN("<"),
	LESS_THAN_EQUALS("<=");
	
	private String literal;
	
	SearchOperation(String value) {
		this.literal = value;
	}
	
	public static SearchOperation fromString(String value) {
		for (SearchOperation searchOps : SearchOperation.values()) {
			if (searchOps.literal.equalsIgnoreCase(value)) {
				return searchOps;
			}
		}
		
		return null;
	}

}
