##  bidirectional association
Пример двусторонней ассоциации

@Embeddable
public class PostTagId оставной ключ
postId
tagId
Замечания

There are two very important
-You need the @Embeddable type to be Serializable
 The @Embeddable type must override the default equals and hashCode methods based on the two Primary Key identifier values.
--------------
PostTag таблица связка
    @EmbeddedId
    private PostTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    private Tag tag;

 --------------------------------
 со стороны Tag @OneToMany
 private List<PostTag> posts = new ArrayList<>();

со стороны Post
@OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PostTag> tags = new ArrayList<>();

    имеет служебные методы **addTag* и **removeTag**



