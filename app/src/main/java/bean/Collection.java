package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Collection {


    @Id(autoincrement = true)
    private Long id;

    private String learningOrExam;//学习类还是试题类

    private String sortType;//分类

    private Long originId;//原始id

    private Long userId;//对应用户id

    @Generated(hash = 1842391945)
    public Collection(Long id, String learningOrExam, String sortType,
            Long originId, Long userId) {
        this.id = id;
        this.learningOrExam = learningOrExam;
        this.sortType = sortType;
        this.originId = originId;
        this.userId = userId;
    }

    @Generated(hash = 1149123052)
    public Collection() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLearningOrExam() {
        return this.learningOrExam;
    }

    public void setLearningOrExam(String learningOrExam) {
        this.learningOrExam = learningOrExam;
    }

    public String getSortType() {
        return this.sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Long getOriginId() {
        return this.originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
