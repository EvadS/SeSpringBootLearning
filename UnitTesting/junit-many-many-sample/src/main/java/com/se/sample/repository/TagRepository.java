package com.se.sample.repository;


import com.se.sample.dto.test;
import com.se.sample.entity.PostTag;
import com.se.sample.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    @Query(value = "SELECT u FROM #{#entityName} u WHERE  u.id > ?1 ORDER BY id")
    List<PostTag> existsTags(Long id);

    @Query("Select b from Tag a  left join PostTag b on a.id=b.tag  WHERE  a.id = :id")
    List<PostTag> existsPostTagsByTag(@Param("id") Long id);


    @Query("SELECT pt  FROM PostTag pt " +
        "WHERE pt.post.id = :id " +
       "      AND pt.tag.id NOT IN (" +
        "SELECT   pt3.tag.id FROM PostTag pt3 " +
        "WHERE  pt3.tag.id IN " +
         "(SELECT pot.tag.id FROM PostTag pot WHERE pot.post.id = :id  ) " +
        " AND pt3.post.id != :id  )"
    )
    List<PostTag> getUnusedTags(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("delete PostTag pt where pt.post.id=:id")
    void deleteRelatedTag(@Param("id") Long id);

}