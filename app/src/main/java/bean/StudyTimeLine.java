package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StudyTimeLine {

    @Id(autoincrement = true)
    private Long id;

    private String time;

    private String activityText;

    private Long userId;

    @Generated(hash = 1908581141)
    public StudyTimeLine(Long id, String time, String activityText, Long userId) {
        this.id = id;
        this.time = time;
        this.activityText = activityText;
        this.userId = userId;
    }

    @Generated(hash = 2094445588)
    public StudyTimeLine() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
