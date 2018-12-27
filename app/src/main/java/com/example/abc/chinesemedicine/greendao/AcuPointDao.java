package com.example.abc.chinesemedicine.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import bean.AcuPoint;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACU_POINT".
*/
public class AcuPointDao extends AbstractDao<AcuPoint, Long> {

    public static final String TABLENAME = "ACU_POINT";

    /**
     * Properties of entity AcuPoint.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property SortType = new Property(2, String.class, "sortType", false, "SORT_TYPE");
        public final static Property ImageUrl = new Property(3, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property Data = new Property(4, String.class, "data", false, "DATA");
    }


    public AcuPointDao(DaoConfig config) {
        super(config);
    }
    
    public AcuPointDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACU_POINT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SORT_TYPE\" TEXT," + // 2: sortType
                "\"IMAGE_URL\" TEXT," + // 3: imageUrl
                "\"DATA\" TEXT);"); // 4: data
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACU_POINT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AcuPoint entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sortType = entity.getSortType();
        if (sortType != null) {
            stmt.bindString(3, sortType);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(4, imageUrl);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(5, data);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AcuPoint entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sortType = entity.getSortType();
        if (sortType != null) {
            stmt.bindString(3, sortType);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(4, imageUrl);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(5, data);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AcuPoint readEntity(Cursor cursor, int offset) {
        AcuPoint entity = new AcuPoint( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sortType
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // imageUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // data
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AcuPoint entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSortType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImageUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setData(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AcuPoint entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AcuPoint entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AcuPoint entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
