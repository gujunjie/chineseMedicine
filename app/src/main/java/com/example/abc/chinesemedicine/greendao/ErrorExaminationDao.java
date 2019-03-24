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

import bean.ErrorExamination;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ERROR_EXAMINATION".
*/
public class ErrorExaminationDao extends AbstractDao<ErrorExamination, Long> {

    public static final String TABLENAME = "ERROR_EXAMINATION";

    /**
     * Properties of entity ErrorExamination.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SortType = new Property(1, String.class, "sortType", false, "SORT_TYPE");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property SectionA = new Property(3, String.class, "sectionA", false, "SECTION_A");
        public final static Property SectionB = new Property(4, String.class, "sectionB", false, "SECTION_B");
        public final static Property SectionC = new Property(5, String.class, "sectionC", false, "SECTION_C");
        public final static Property SectionD = new Property(6, String.class, "sectionD", false, "SECTION_D");
        public final static Property SectionE = new Property(7, String.class, "sectionE", false, "SECTION_E");
        public final static Property CorrectSection = new Property(8, String.class, "correctSection", false, "CORRECT_SECTION");
        public final static Property Answer = new Property(9, String.class, "answer", false, "ANSWER");
        public final static Property ExamId = new Property(10, Long.class, "examId", false, "EXAM_ID");
        public final static Property UserId = new Property(11, Long.class, "userId", false, "USER_ID");
        public final static Property RightTimes = new Property(12, int.class, "rightTimes", false, "RIGHT_TIMES");
    }

    private Query<ErrorExamination> user_ErrorExaminationListQuery;

    public ErrorExaminationDao(DaoConfig config) {
        super(config);
    }
    
    public ErrorExaminationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ERROR_EXAMINATION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SORT_TYPE\" TEXT," + // 1: sortType
                "\"TITLE\" TEXT," + // 2: title
                "\"SECTION_A\" TEXT," + // 3: sectionA
                "\"SECTION_B\" TEXT," + // 4: sectionB
                "\"SECTION_C\" TEXT," + // 5: sectionC
                "\"SECTION_D\" TEXT," + // 6: sectionD
                "\"SECTION_E\" TEXT," + // 7: sectionE
                "\"CORRECT_SECTION\" TEXT," + // 8: correctSection
                "\"ANSWER\" TEXT," + // 9: answer
                "\"EXAM_ID\" INTEGER," + // 10: examId
                "\"USER_ID\" INTEGER," + // 11: userId
                "\"RIGHT_TIMES\" INTEGER NOT NULL );"); // 12: rightTimes
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ERROR_EXAMINATION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ErrorExamination entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sortType = entity.getSortType();
        if (sortType != null) {
            stmt.bindString(2, sortType);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String sectionA = entity.getSectionA();
        if (sectionA != null) {
            stmt.bindString(4, sectionA);
        }
 
        String sectionB = entity.getSectionB();
        if (sectionB != null) {
            stmt.bindString(5, sectionB);
        }
 
        String sectionC = entity.getSectionC();
        if (sectionC != null) {
            stmt.bindString(6, sectionC);
        }
 
        String sectionD = entity.getSectionD();
        if (sectionD != null) {
            stmt.bindString(7, sectionD);
        }
 
        String sectionE = entity.getSectionE();
        if (sectionE != null) {
            stmt.bindString(8, sectionE);
        }
 
        String correctSection = entity.getCorrectSection();
        if (correctSection != null) {
            stmt.bindString(9, correctSection);
        }
 
        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(10, answer);
        }
 
        Long examId = entity.getExamId();
        if (examId != null) {
            stmt.bindLong(11, examId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(12, userId);
        }
        stmt.bindLong(13, entity.getRightTimes());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ErrorExamination entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String sortType = entity.getSortType();
        if (sortType != null) {
            stmt.bindString(2, sortType);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String sectionA = entity.getSectionA();
        if (sectionA != null) {
            stmt.bindString(4, sectionA);
        }
 
        String sectionB = entity.getSectionB();
        if (sectionB != null) {
            stmt.bindString(5, sectionB);
        }
 
        String sectionC = entity.getSectionC();
        if (sectionC != null) {
            stmt.bindString(6, sectionC);
        }
 
        String sectionD = entity.getSectionD();
        if (sectionD != null) {
            stmt.bindString(7, sectionD);
        }
 
        String sectionE = entity.getSectionE();
        if (sectionE != null) {
            stmt.bindString(8, sectionE);
        }
 
        String correctSection = entity.getCorrectSection();
        if (correctSection != null) {
            stmt.bindString(9, correctSection);
        }
 
        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(10, answer);
        }
 
        Long examId = entity.getExamId();
        if (examId != null) {
            stmt.bindLong(11, examId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(12, userId);
        }
        stmt.bindLong(13, entity.getRightTimes());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ErrorExamination readEntity(Cursor cursor, int offset) {
        ErrorExamination entity = new ErrorExamination( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sortType
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sectionA
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sectionB
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // sectionC
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // sectionD
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // sectionE
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // correctSection
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // answer
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // examId
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11), // userId
            cursor.getInt(offset + 12) // rightTimes
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ErrorExamination entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSortType(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSectionA(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSectionB(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSectionC(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSectionD(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSectionE(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCorrectSection(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAnswer(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setExamId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setUserId(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.setRightTimes(cursor.getInt(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ErrorExamination entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ErrorExamination entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ErrorExamination entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "errorExaminationList" to-many relationship of User. */
    public List<ErrorExamination> _queryUser_ErrorExaminationList(Long userId) {
        synchronized (this) {
            if (user_ErrorExaminationListQuery == null) {
                QueryBuilder<ErrorExamination> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                user_ErrorExaminationListQuery = queryBuilder.build();
            }
        }
        Query<ErrorExamination> query = user_ErrorExaminationListQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

}
