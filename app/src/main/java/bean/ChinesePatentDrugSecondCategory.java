package bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChinesePatentDrugSecondCategory {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String firstCategoryName;

    @Generated(hash = 1970527396)
    public ChinesePatentDrugSecondCategory(Long id, String name,
            String firstCategoryName) {
        this.id = id;
        this.name = name;
        this.firstCategoryName = firstCategoryName;
    }

    @Generated(hash = 834367277)
    public ChinesePatentDrugSecondCategory() {
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

    public String getFirstCategoryName() {
        return this.firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

}
