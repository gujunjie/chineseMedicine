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
import com.example.abc.chinesemedicine.greendao.ErrorExaminationDao;
import com.example.abc.chinesemedicine.greendao.CollectionDao;
import com.example.abc.chinesemedicine.greendao.ExamProgressDao;

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    private String name;//用户名

    private String userIconPath;//用户头像

    private String motto;//座右铭

    private int rightTimesForRemove=-1;//错题做对几次就删除,默认-1不删除

    private String lastestLearningSubject;//最后一次学习的分类科目

    private String lastestLearningItem;//最后一次学习的分类章节


    @ToMany(referencedJoinProperty = "userId")
    private List<LearningProgress> list;//各个分类的学习进度

    @ToMany(referencedJoinProperty = "userId")
    private List<ExamProgress> list2;//各个分类的测试进度


    @ToMany(referencedJoinProperty = "userId")
    private List<ErrorExamination> errorExaminationList;//错题

    @ToMany(referencedJoinProperty = "userId")
    private List<Collection> collectionList;//收藏

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;


    @Generated(hash = 1396084763)
    public User(Long id, String name, String userIconPath, String motto, int rightTimesForRemove,
            String lastestLearningSubject, String lastestLearningItem) {
        this.id = id;
        this.name = name;
        this.userIconPath = userIconPath;
        this.motto = motto;
        this.rightTimesForRemove = rightTimesForRemove;
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


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2131332013)
    public List<ErrorExamination> getErrorExaminationList() {
        if (errorExaminationList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ErrorExaminationDao targetDao = daoSession.getErrorExaminationDao();
            List<ErrorExamination> errorExaminationListNew = targetDao
                    ._queryUser_ErrorExaminationList(id);
            synchronized (this) {
                if (errorExaminationList == null) {
                    errorExaminationList = errorExaminationListNew;
                }
            }
        }
        return errorExaminationList;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1600889196)
    public synchronized void resetErrorExaminationList() {
        errorExaminationList = null;
    }


    public int getRightTimesForRemove() {
        return this.rightTimesForRemove;
    }


    public void setRightTimesForRemove(int rightTimesForRemove) {
        this.rightTimesForRemove = rightTimesForRemove;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1550220311)
    public List<Collection> getCollectionList() {
        if (collectionList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CollectionDao targetDao = daoSession.getCollectionDao();
            List<Collection> collectionListNew = targetDao._queryUser_CollectionList(id);
            synchronized (this) {
                if (collectionList == null) {
                    collectionList = collectionListNew;
                }
            }
        }
        return collectionList;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1259317886)
    public synchronized void resetCollectionList() {
        collectionList = null;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1276169164)
    public List<ExamProgress> getList2() {
        if (list2 == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ExamProgressDao targetDao = daoSession.getExamProgressDao();
            List<ExamProgress> list2New = targetDao._queryUser_List2(id);
            synchronized (this) {
                if (list2 == null) {
                    list2 = list2New;
                }
            }
        }
        return list2;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 576577739)
    public synchronized void resetList2() {
        list2 = null;
    }





}
