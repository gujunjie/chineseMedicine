package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.abc.chinesemedicine.greendao.DaoSession;
import com.example.abc.chinesemedicine.greendao.LearningProgressDao;
import com.example.abc.chinesemedicine.greendao.UserDao;

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    private String name;//用户名

    private String userIconPath;//用户头像

    private String motto;//座右铭

    private String lastestLearningSubject;//最后一次学习的分类科目

    private String lastestLearningItem;//最后一次学习的分类章节


    @ToMany(referencedJoinProperty = "userId")
    private List<LearningProgress> list;//各个分类的学习进度

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;


    @Generated(hash = 973927970)
    public User(Long id, String name, String userIconPath, String motto,
            String lastestLearningSubject, String lastestLearningItem) {
        this.id = id;
        this.name = name;
        this.userIconPath = userIconPath;
        this.motto = motto;
        this.lastestLearningSubject = lastestLearningSubject;
        this.lastestLearningItem = lastestLearningItem;
    }


    @Generated(hash = 586692638)
    public User() {
    }


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


    public String getUserIconPath() {
        return this.userIconPath;
    }


    public void setUserIconPath(String userIconPath) {
        this.userIconPath = userIconPath;
    }


    public String getMotto() {
        return this.motto;
    }


    public void setMotto(String motto) {
        this.motto = motto;
    }


    public String getLastestLearningSubject() {
        return this.lastestLearningSubject;
    }


    public void setLastestLearningSubject(String lastestLearningSubject) {
        this.lastestLearningSubject = lastestLearningSubject;
    }


    public String getLastestLearningItem() {
        return this.lastestLearningItem;
    }


    public void setLastestLearningItem(String lastestLearningItem) {
        this.lastestLearningItem = lastestLearningItem;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 742566787)
    public List<LearningProgress> getList() {
        if (list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LearningProgressDao targetDao = daoSession.getLearningProgressDao();
            List<LearningProgress> listNew = targetDao._queryUser_List(id);
            synchronized (this) {
                if (list == null) {
                    list = listNew;
                }
            }
        }
        return list;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 589833612)
    public synchronized void resetList() {
        list = null;
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }





}
