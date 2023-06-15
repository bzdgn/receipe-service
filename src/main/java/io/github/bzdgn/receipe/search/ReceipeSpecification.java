package io.github.bzdgn.receipe.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import io.github.bzdgn.receipe.dao.Receipe;

public class ReceipeSpecification implements Specification<Receipe> {

	private static final long serialVersionUID = 8471405593486904071L;
	
	private final SearchCriteria searchCriteria;

    public ReceipeSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Receipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        switch(SearchOperation.fromString(searchCriteria.getOperation())){
            case CONTAINS:
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + searchCriteria.getValue().toString().toLowerCase() + "%");

            case NOT_CONTAINS:
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + searchCriteria.getValue().toString().toLowerCase() + "%");

            case EQUALS:
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUALS:
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case GREATER_THAN:
                return cb.greaterThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUALS:
                return cb.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                return cb.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUALS:
                return cb.lessThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
        }
        
        return null;
    }

}
