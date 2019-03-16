package bean;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Note implements Parcelable {


    @Id(autoincrement = true)
    private Long id;

    private String userName;
    
    private String noteText;

    private String subject;

    private String title;

    private String time;

    @Generated(hash = 183559138)
    public Note(Long id, String userName, String noteText, String subject,
            String title, String time) {
        this.id = id;
        this.userName = userName;
        this.noteText = noteText;
        this.subject = subject;
        this.title = title;
        this.time = time;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    protected Note(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        userName = in.readString();
        noteText = in.readString();
        subject = in.readString();
        title = in.readString();
        time = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoteText() {
        return this.noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
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
        parcel.writeString(userName);
        parcel.writeString(noteText);
        parcel.writeString(subject);
        parcel.writeString(title);
        parcel.writeString(time);
    }
}
