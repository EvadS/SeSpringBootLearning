package com.se.sample.repository;


import com.se.sample.entity.PostTag;
import com.se.sample.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    //    @Query("SELECT c FROM PostTag c INNER JOIN c.p o WHERE c.status = 1")
    @Query(value = "SELECT u FROM #{#entityName} u WHERE  u.id > ?1 ORDER BY id")
    List<PostTag> existsTags(Long id);


    @Query("Select b from Tag a  left join PostTag b on a.id=b.tag  WHERE  a.id = :id")
    List<PostTag> existsPostTagsByTag(@Param("id") Long id);

    @Query("Select b from Tag a  left join PostTag b on a.id=b.tag  WHERE  a.id = :id")
    List<PostTag> getDeattachedPostList(@Param("id") Long id);


}