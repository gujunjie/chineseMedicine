package bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class SearchHistory {

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String history;

    @Generated(hash = 1690062961)
    public SearchHistory(Long id, String history) {
        this.id = id;
        this.history = history;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public SearchHistory(String history) {
        this.history = history;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
