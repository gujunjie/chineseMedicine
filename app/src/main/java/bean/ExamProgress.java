package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ExamProgress {

    @Id(autoincrement = true)
    private Long id;

    private String examSubject;//测试科目

    private int lastExamPosition;//最近测试位置进度

    private float lastExamPercent;//最近测试百分比进度

    private Long userId;

    @Generated(hash = 1248078815)
    public ExamProgress(Long id, String examSubject, int lastExamPosition,
            float lastExamPercent, Long userId) {
        this.id = id;
        this.examSubject = examSubject;
        this.lastExamPosition = lastExamPosition;
        this.lastExamPercent = lastExamPercent;
        this.userId = userId;
    }

    @Generated(hash = 1839499577)
    public ExamProgress() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamSubject() {
        return this.examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
    }

    public int getLastExamPosition() {
        return this.lastExamPosition;
    }

    public void setLastExamPosition(int lastExamPosition) {
        this.lastExamPosition = lastExamPosition;
    }

    public float getLastExamPercent() {
        return this.lastExamPercent;
    }

    public void setLastExamPercent(float lastExamPercent) {
        this.lastExamPercent = lastExamPercent;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
