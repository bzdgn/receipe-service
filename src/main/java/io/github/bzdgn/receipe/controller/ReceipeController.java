package io.github.bzdgn.receipe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.bzdgn.receipe.dao.Receipe;
import io.github.bzdgn.receipe.dao.ReceipeRepository;
import io.github.bzdgn.receipe.search.ReceipeSearchDTO;
import io.github.bzdgn.receipe.search.ReceipeSpecificationBuilder;
import io.github.bzdgn.receipe.search.SearchCriteria;
import io.swagger.annotations.ApiOperation;

@RestController
public class ReceipeController {

	private ReceipeRepository receipeRepository;

	public ReceipeController(ReceipeRepository receipeRepository) {
		this.receipeRepository = receipeRepository;
	}

	/*
	 * Fetch operations
	 */
	@ApiOperation(value = "Get receipes", notes = "Bulk retrieval of the receipes.")
	@RequestMapping(value = "/receipe", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Receipe> getReceipes() {
		List<Receipe> resp =  this.receipeRepository.findAll();
		
		return resp;
	}

	@ApiOperation(value = "Get Receipe by name", notes = "Gets by uniquely specified receipe name.")
	@GetMapping("/receipe/{name}")
	public Receipe getReceipeByName(@PathVariable(value = "name") String name) {
		return receipeRepository.findByName(name);
	}

	@ApiOperation(value = "Search receipe", notes = "Search receipes by parameters.")
	@RequestMapping(value = "/receipe/search", method = RequestMethod.POST)
	public List<Receipe> searchReceipes(@RequestBody ReceipeSearchDTO receipeSearchDTO) {
		ReceipeSpecificationBuilder builder = new ReceipeSpecificationBuilder();
        List<SearchCriteria> criteriaList = receipeSearchDTO.getSearchCriteriaList();
        if(criteriaList != null){
            criteriaList.forEach(x-> builder.with(x));
        }

        return receipeRepository.findAll(builder.build());
	}

	/*
	 * add Receipe
	 */
	@ApiOperation(value = "Add a receipe", notes = "Posts a receipe json body and persists to the database.")
	@PostMapping(value = "/receipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receipe> addReceipe(@RequestBody Receipe receipe) {
		Receipe created = this.receipeRepository.save(receipe);
		
		return new ResponseEntity<Receipe>(this.receipeRepository.save(receipe), created == null ? HttpStatus.NO_CONTENT : HttpStatus.CREATED);
	}
	
	/*
	 * modify Receipe
	 */
	@ApiOperation(value = "Add a receipe", notes = "Posts a receipe json body and persists to the database.")
	@PatchMapping(value = "/receipe/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Receipe> addReceipe(@PathVariable(value = "id") Long id, @RequestBody Receipe receipe) {
		Receipe existing = null;
		
		if (this.receipeRepository.findById(id).isPresent()) {
			existing = this.receipeRepository.findById(id).get();
			
			existing.setIngredients(receipe.getIngredients());
			existing.setInstructions(receipe.getInstructions());
			existing.setName(receipe.getName());
			existing.setServing(receipe.getServing());
			existing.setVegan(receipe.isVegan());
		}
		
		return new ResponseEntity<Receipe>(this.receipeRepository.save(existing), existing == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/*
	 * delete receipe
	 */
	@ApiOperation(value = "Delete a receipe by name", notes = "Delete the receipe by uniquely specified receipe name")
	@RequestMapping(value = "/receipe/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteReceipe(@PathVariable(value = "name") String name) {
		int deleted = this.receipeRepository.deleteByName(name);
		
		return new ResponseEntity<Integer>(deleted, deleted == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
}
