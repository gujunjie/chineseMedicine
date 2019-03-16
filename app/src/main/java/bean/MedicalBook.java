package bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MedicalBook implements Parcelable {


    @Id(autoincrement = true)
    private Long id;

    private String bookName;

    private String name;

    private String imageUrl;

    private String data;

    private String sortType;

    @Generated(hash = 573549085)
    public MedicalBook(Long id, String bookName, String name, String imageUrl,
            String data, String sortType) {
        this.id = id;
        this.bookName = bookName;
        this.name = name;
        this.imageUrl = imageUrl;
        this.data = data;
        this.sortType = sortType;
    }

    @Generated(hash = 906443357)
    public MedicalBook() {
    }

    protected MedicalBook(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        bookName = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        data = in.readString();
        sortType = in.readString();
    }

    public static final Creator<MedicalBook> CREATOR = new Creator<MedicalBook>() {
        @Override
        public MedicalBook createFromParcel(Parcel in) {
            return new MedicalBook(in);
        }

        @Override
        public MedicalBook[] newArray(int size) {
            return new MedicalBook[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
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
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(bookName);
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeString(data);
        parcel.writeString(sortType);
    }
}
