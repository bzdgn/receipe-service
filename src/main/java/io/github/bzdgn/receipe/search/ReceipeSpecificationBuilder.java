package io.github.bzdgn.receipe.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import io.github.bzdgn.receipe.dao.Receipe;

public class ReceipeSpecificationBuilder {

    private final List<SearchCriteria> params;

    public ReceipeSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final ReceipeSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final ReceipeSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Receipe> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Receipe> result = new ReceipeSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = Specification.where(result).and(new ReceipeSpecification(criteria));
        }

        return result;
    }
}
