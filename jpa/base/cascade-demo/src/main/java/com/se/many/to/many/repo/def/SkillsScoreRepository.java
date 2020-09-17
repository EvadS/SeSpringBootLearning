package com.se.many.to.many.repo.def;

import com.se.many.to.many.entity.def.SkillsScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsScoreRepository extends JpaRepository<SkillsScore, Long> {

}
