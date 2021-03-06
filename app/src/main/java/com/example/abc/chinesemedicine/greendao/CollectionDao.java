package com.example.abc.chinesemedicine.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import bean.Collection;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COLLECTION".
*/
public class CollectionDao extends AbstractDao<Collection, Long> {

    public static final String TABLENAME = "COLLECTION";

    /**
     * Properties of entity Collection.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property LearningOrExam = new Property(1, String.class, "learningOrExam", false, "LEARNING_OR_EXAM");
        public final static Property SortType = new Property(2, String.class, "sortType", false, "SORT_TYPE");
        public final static Property OriginId = new Property(3, Long.class, "originId", false, "ORIGIN_ID");
        public final static Property UserId = new Property(4, Long.class, "userId", false, "USER_ID");
    }

    private Query<Collection> user_CollectionListQuery;

    public CollectionDao(DaoConfig config) {
        super(config);
    }
    
    public CollectionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COLLECTION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"LEARNING_OR_EXAM\" TEXT," + // 1: learningOrExam
                "\"SORT_TYPE\" TEXT," + // 2: sortType
                "\"ORIGIN_ID\" INTEGER," + // 3: originId
                "\"USER_ID\" INTEGER);"); // 4: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COLLECTION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Collection entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String learningOrExam = entity.getLearningOrExam();
        if (learningOrExam != null) {
            stmt.bindString(2, learningOrExam);
        }
 
        String sortType = entity.getSortType();
        if (sortType != null) {
            stmt.bindString(3, sortType);
        }
 
        Long originId = entity.getOriginId();
        if (originId != null) {
            stmt.bindLong(4, originId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Collection entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String learningOrExam = entity.getLearningOrExam();
        if (learningOrExam != null) {
            stmt.bindString(2, learningOrExam);
        }
 
        String sortType = entity.getSortType();
        if (sortType != null) {
            stmt.bindString(3, sortType);
        }
 
        Long originId = entity.getOriginId();
        if (originId != null) {
            stmt.bindLong(4, originId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Collection readEntity(Cursor cursor, int offset) {
        Collection entity = new Collection( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // learningOrExam
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sortType
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // originId
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // userId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Collection entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLearningOrExam(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSortType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOriginId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Collection entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Collection entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Collection entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "collectionList" to-many relationship of User. */
    public List<Collection> _queryUser_CollectionList(Long userId) {
        synchronized (this) {
            if (user_CollectionListQuery == null) {
                QueryBuilder<Collection> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                user_CollectionListQuery = queryBuilder.build();
            }
        }
        Query<Collection> query = user_CollectionListQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

}
