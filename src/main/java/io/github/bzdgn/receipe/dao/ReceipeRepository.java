package io.github.bzdgn.receipe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReceipeRepository extends JpaRepository <Receipe, Long>, JpaSpecificationExecutor<Receipe> {
	
	Receipe findByName(String name);
	List<Receipe> findAll();
	
	@Transactional
	@Modifying
	Integer deleteByName(String name);
	
}
