package com.se.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "posts")
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
    @JsonIgnore
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    @JsonIgnore
    private Date lastUpdatedAt = new Date();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostTag> postTags;


    public Post() {

    }

    /**
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
     *
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

    /**
     *
     * @param title
     * @param description
     * @param content
     * @param bookPublishers
     */
    public Post(@NotNull @Size(max = 100) String title,
                @NotNull @Size(max = 250) String description,
                @NotNull String content, PostTag... bookPublishers) {
        this.title = title;
        this.description = description;
        this.content = content;

        buildPostTags(bookPublishers);
    }

    public Post(String title, String description, String content, List<PostTag> postTagsList) {

        this.title = title;
        this.description = description;
        this.content = content;

        for(PostTag bookPublisher : postTagsList) {
            bookPublisher.setPost(this);
        }
        this.postTags =   postTagsList.stream().collect(Collectors.toSet());
    }


    public void buildPostTags(PostTag... bookPublishers){
        for(PostTag bookPublisher : bookPublishers) {
            bookPublisher.setPost(this);
        }

        this.postTags = Stream.of(bookPublishers).collect(Collectors.toSet());
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

    public Set<PostTag> getPostTags() {
        return postTags;
    }
}
