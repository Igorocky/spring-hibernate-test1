package igye.springhibernate.model.collections;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(SAVE_UPDATE)
    private Folder parent;

    private String filePath;

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return filePath != null ? filePath.equals(image.filePath) : image.filePath == null;
    }

    @Override
    public int hashCode() {
        return filePath != null ? filePath.hashCode() : 0;
    }
}
