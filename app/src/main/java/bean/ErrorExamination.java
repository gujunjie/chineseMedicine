package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ErrorExamination {


    @Id(autoincrement = true)
    private Long id;

    private String sortType;
    private String title;
    private String sectionA;
    private String sectionB;
    private String sectionC;
    private String sectionD;
    private String sectionE;
    private String correctSection;
    private String answer;
    private Long examId;
    private Long userId;

    private int rightTimes;

    @Generated(hash = 1449813913)
    public ErrorExamination(Long id, String sortType, String title, String sectionA,
            String sectionB, String sectionC, String sectionD, String sectionE,
            String correctSection, String answer, Long examId, Long userId,
            int rightTimes) {
        this.id = id;
        this.sortType = sortType;
        this.title = title;
        this.sectionA = sectionA;
        this.sectionB = sectionB;
        this.sectionC = sectionC;
        this.sectionD = sectionD;
        this.sectionE = sectionE;
        this.correctSection = correctSection;
        this.answer = answer;
        this.examId = examId;
        this.userId = userId;
        this.rightTimes = rightTimes;
    }
    @Generated(hash = 2065370453)
    public ErrorExamination() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSortType() {
        return this.sortType;
    }
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSectionA() {
        return this.sectionA;
    }
    public void setSectionA(String sectionA) {
        this.sectionA = sectionA;
    }
    public String getSectionB() {
        return this.sectionB;
    }
    public void setSectionB(String sectionB) {
        this.sectionB = sectionB;
    }
    public String getSectionC() {
        return this.sectionC;
    }
    public void setSectionC(String sectionC) {
        this.sectionC = sectionC;
    }
    public String getSectionD() {
        return this.sectionD;
    }
    public void setSectionD(String sectionD) {
        this.sectionD = sectionD;
    }
    public String getSectionE() {
        return this.sectionE;
    }
    public void setSectionE(String sectionE) {
        this.sectionE = sectionE;
    }
    public String getCorrectSection() {
        return this.correctSection;
    }
    public void setCorrectSection(String correctSection) {
        this.correctSection = correctSection;
    }
    public String getAnswer() {
        return this.answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getExamId() {
        return this.examId;
    }
    public void setExamId(Long examId) {
        this.examId = examId;
    }
    public int getRightTimes() {
        return this.rightTimes;
    }
    public void setRightTimes(int rightTimes) {
        this.rightTimes = rightTimes;
    }


}
