package com.se.sample.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    @Lob
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();


//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "post_tags",
//            joinColumns = { @JoinColumn(name = "post_id") },
//            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
//    @JsonManagedReference
//    private Set<Tag> tags;


    public Post() {

    }

    /**
     *
     * @param title
     * @param description
     * @param content
     */
    public Post(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    /**
     * parametrized constructor for unit testing
     * @param id
     * @param title
     * @param description
     * @param content
     */
    public Post(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

  //  public Set<Tag> getTags() {
//        return tags;
//    }

//    public void setTags(Set<Tag> tags) {
//        this.tags = tags;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(description, post.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

//    public void addTag(Tag tag){
//        if (tags == null) {
//            tags = new HashSet<>();
//        }
//
//        tags.add(tag);
//    }


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostTag> postTags;

    public Set<PostTag> getPostTags() {
        return postTags;
    }

    public void setPostTags(Set<PostTag> postTags) {
        this.postTags = postTags;
    }
}
