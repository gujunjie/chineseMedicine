package bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class ChineseMedicine implements Parcelable{

    //中药类

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String medicineImageUrl;

    private String sortType;
    
    private String data;



    @Generated(hash = 1800810230)
    public ChineseMedicine(Long id, String name, String medicineImageUrl,
            String sortType, String data) {
        this.id = id;
        this.name = name;
        this.medicineImageUrl = medicineImageUrl;
        this.sortType = sortType;
        this.data = data;
    }

    @Generated(hash = 2031520550)
    public ChineseMedicine() {
    }

    protected ChineseMedicine(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        medicineImageUrl = in.readString();
        sortType = in.readString();
        data = in.readString();
    }

    public static final Creator<ChineseMedicine> CREATOR = new Creator<ChineseMedicine>() {
        @Override
        public ChineseMedicine createFromParcel(Parcel in) {
            return new ChineseMedicine(in);
        }

        @Override
        public ChineseMedicine[] newArray(int size) {
            return new ChineseMedicine[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineImageUrl() {
        return this.medicineImageUrl;
    }

    public void setMedicineImageUrl(String medicineImageUrl) {
        this.medicineImageUrl = medicineImageUrl;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChineseMedicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", medicineImageUrl='" + medicineImageUrl + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getSortType() {
        return this.sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
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
        dest.writeString(medicineImageUrl);
        dest.writeString(sortType);
        dest.writeString(data);
    }
}
