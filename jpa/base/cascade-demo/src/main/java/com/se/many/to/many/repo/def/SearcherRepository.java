package com.se.many.to.many.repo.def;


import com.se.many.to.many.entity.def.Searcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SearcherRepository extends JpaRepository<Searcher, Long>{

}