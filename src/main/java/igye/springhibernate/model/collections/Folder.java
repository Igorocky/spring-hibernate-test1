package igye.springhibernate.model.collections;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Folder {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection
    @AttributeOverride(
            name = "filePath",
            column = @Column(name = "fpath")
    )
    @OrderBy("fpath DESC")
    protected Set<Image> images = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
