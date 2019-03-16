package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LearningProgress {


    @Id(autoincrement = true)
    private Long id;

    private String learningSubject;//学习科目

    private int lastLearningPosition;//最近学习位置进度

    private float lastLearningPercent;//最近学习百分比进度

    private Long userId;

    @Generated(hash = 758437045)
    public LearningProgress(Long id, String learningSubject,
            int lastLearningPosition, float lastLearningPercent, Long userId) {
        this.id = id;
        this.learningSubject = learningSubject;
        this.lastLearningPosition = lastLearningPosition;
        this.lastLearningPercent = lastLearningPercent;
        this.userId = userId;
    }

    @Generated(hash = 919042380)
    public LearningProgress() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLearningSubject() {
        return this.learningSubject;
    }

    public void setLearningSubject(String learningSubject) {
        this.learningSubject = learningSubject;
    }

    public int getLastLearningPosition() {
        return this.lastLearningPosition;
    }

    public void setLastLearningPosition(int lastLearningPosition) {
        this.lastLearningPosition = lastLearningPosition;
    }

    public float getLastLearningPercent() {
        return this.lastLearningPercent;
    }

    public void setLastLearningPercent(float lastLearningPercent) {
        this.lastLearningPercent = lastLearningPercent;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
