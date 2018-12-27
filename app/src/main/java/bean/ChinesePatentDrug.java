package bean;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChinesePatentDrug implements Parcelable{

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String imageUrl;

    private String secondCategoryName;

    private String data;

    @Generated(hash = 1611388503)
    public ChinesePatentDrug(Long id, String name, String imageUrl,
            String secondCategoryName, String data) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.secondCategoryName = secondCategoryName;
        this.data = data;
    }

    @Generated(hash = 1872880411)
    public ChinesePatentDrug() {
    }

    protected ChinesePatentDrug(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        imageUrl = in.readString();
        secondCategoryName = in.readString();
        data = in.readString();
    }

    public static final Creator<ChinesePatentDrug> CREATOR = new Creator<ChinesePatentDrug>() {
        @Override
        public ChinesePatentDrug createFromParcel(Parcel in) {
            return new ChinesePatentDrug(in);
        }

        @Override
        public ChinesePatentDrug[] newArray(int size) {
            return new ChinesePatentDrug[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSecondCategoryName() {
        return this.secondCategoryName;
    }

    public void setSecondCategoryName(String secondCategoryName) {
        this.secondCategoryName = secondCategoryName;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
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
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(secondCategoryName);
        dest.writeString(data);
    }
}
