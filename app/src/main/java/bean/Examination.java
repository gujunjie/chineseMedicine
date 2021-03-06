package bean;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Examination implements Parcelable {


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
    private String imageUrl;
    @Generated(hash = 248051520)
    public Examination(Long id, String sortType, String title, String sectionA,
            String sectionB, String sectionC, String sectionD, String sectionE,
            String correctSection, String answer, String imageUrl) {
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
        this.imageUrl = imageUrl;
    }
    @Generated(hash = 1518633012)
    public Examination() {
    }

    protected Examination(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        sortType = in.readString();
        title = in.readString();
        sectionA = in.readString();
        sectionB = in.readString();
        sectionC = in.readString();
        sectionD = in.readString();
        sectionE = in.readString();
        correctSection = in.readString();
        answer = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Examination> CREATOR = new Creator<Examination>() {
        @Override
        public Examination createFromParcel(Parcel in) {
            return new Examination(in);
        }

        @Override
        public Examination[] newArray(int size) {
            return new Examination[size];
        }
    };

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
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(sortType);
        dest.writeString(title);
        dest.writeString(sectionA);
        dest.writeString(sectionB);
        dest.writeString(sectionC);
        dest.writeString(sectionD);
        dest.writeString(sectionE);
        dest.writeString(correctSection);
        dest.writeString(answer);
        dest.writeString(imageUrl);
    }
}
