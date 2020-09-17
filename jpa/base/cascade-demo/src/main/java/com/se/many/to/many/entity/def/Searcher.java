package com.se.many.to.many.entity.def;

import com.se.many.to.many.entity.University;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Searcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable (name="seacher_skills",
            joinColumns=@JoinColumn (name="searcher_id"),
            inverseJoinColumns=@JoinColumn(name="skills_score_id"))
    private List<SkillsScore> skillsScore = new ArrayList<>();

    public Searcher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillsScore> getSkillsScore() {
        return skillsScore;
    }

    public void setSkillsScore(List<SkillsScore> skillsScore) {
        this.skillsScore = skillsScore;
    }
}
