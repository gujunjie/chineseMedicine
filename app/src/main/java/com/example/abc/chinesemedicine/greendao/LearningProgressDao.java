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

import bean.LearningProgress;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LEARNING_PROGRESS".
*/
public class LearningProgressDao extends AbstractDao<LearningProgress, Long> {

    public static final String TABLENAME = "LEARNING_PROGRESS";

    /**
     * Properties of entity LearningProgress.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property LearningSubject = new Property(1, String.class, "learningSubject", false, "LEARNING_SUBJECT");
        public final static Property LastLearningPosition = new Property(2, int.class, "lastLearningPosition", false, "LAST_LEARNING_POSITION");
        public final static Property LastLearningPercent = new Property(3, float.class, "lastLearningPercent", false, "LAST_LEARNING_PERCENT");
        public final static Property UserId = new Property(4, Long.class, "userId", false, "USER_ID");
    }

    private Query<LearningProgress> user_ListQuery;

    public LearningProgressDao(DaoConfig config) {
        super(config);
    }
    
    public LearningProgressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LEARNING_PROGRESS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"LEARNING_SUBJECT\" TEXT," + // 1: learningSubject
                "\"LAST_LEARNING_POSITION\" INTEGER NOT NULL ," + // 2: lastLearningPosition
                "\"LAST_LEARNING_PERCENT\" REAL NOT NULL ," + // 3: lastLearningPercent
                "\"USER_ID\" INTEGER);"); // 4: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LEARNING_PROGRESS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LearningProgress entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String learningSubject = entity.getLearningSubject();
        if (learningSubject != null) {
            stmt.bindString(2, learningSubject);
        }
        stmt.bindLong(3, entity.getLastLearningPosition());
        stmt.bindDouble(4, entity.getLastLearningPercent());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LearningProgress entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String learningSubject = entity.getLearningSubject();
        if (learningSubject != null) {
            stmt.bindString(2, learningSubject);
        }
        stmt.bindLong(3, entity.getLastLearningPosition());
        stmt.bindDouble(4, entity.getLastLearningPercent());
 
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
    public LearningProgress readEntity(Cursor cursor, int offset) {
        LearningProgress entity = new LearningProgress( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // learningSubject
            cursor.getInt(offset + 2), // lastLearningPosition
            cursor.getFloat(offset + 3), // lastLearningPercent
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // userId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LearningProgress entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLearningSubject(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLastLearningPosition(cursor.getInt(offset + 2));
        entity.setLastLearningPercent(cursor.getFloat(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LearningProgress entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LearningProgress entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LearningProgress entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "list" to-many relationship of User. */
    public List<LearningProgress> _queryUser_List(Long userId) {
        synchronized (this) {
            if (user_ListQuery == null) {
                QueryBuilder<LearningProgress> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                user_ListQuery = queryBuilder.build();
            }
        }
        Query<LearningProgress> query = user_ListQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

}
