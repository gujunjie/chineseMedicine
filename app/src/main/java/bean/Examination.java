package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Examination {


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
    @Generated(hash = 792514529)
    public Examination(Long id, String sortType, String title, String sectionA,
            String sectionB, String sectionC, String sectionD, String sectionE,
            String correctSection, String answer) {
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
    }
    @Generated(hash = 1518633012)
    public Examination() {
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






}
