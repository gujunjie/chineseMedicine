package bean;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AcuPoint implements Parcelable{

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String sortType;

    private String imageUrl;

    private String data;

    @Generated(hash = 1186843349)
    public AcuPoint(Long id, String name, String sortType, String imageUrl,
            String data) {
        this.id = id;
        this.name = name;
        this.sortType = sortType;
        this.imageUrl = imageUrl;
        this.data = data;
    }

    @Generated(hash = 87570063)
    public AcuPoint() {
    }

    protected AcuPoint(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        sortType = in.readString();
        imageUrl = in.readString();
        data = in.readString();
    }

    public static final Creator<AcuPoint> CREATOR = new Creator<AcuPoint>() {
        @Override
        public AcuPoint createFromParcel(Parcel in) {
            return new AcuPoint(in);
        }

        @Override
        public AcuPoint[] newArray(int size) {
            return new AcuPoint[size];
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

    public String getSortType() {
        return this.sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        dest.writeString(sortType);
        dest.writeString(imageUrl);
        dest.writeString(data);
    }
}
