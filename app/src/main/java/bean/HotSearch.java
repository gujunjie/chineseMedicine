package bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HotSearch {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    @Generated(hash = 1238044218)
    public HotSearch(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 160356079)
    public HotSearch() {
    }

    public HotSearch(String name) {
        this.name = name;
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
}
