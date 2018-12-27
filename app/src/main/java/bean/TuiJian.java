package bean;

import java.util.List;

public class TuiJian {


    private List<TuiJianBean> TuiJian;

    public List<TuiJianBean> getTuiJian() {
        return TuiJian;
    }

    public void setTuiJian(List<TuiJianBean> TuiJian) {
        this.TuiJian = TuiJian;
    }

    public static class TuiJianBean {
        /**
         * title : 食疗真的能治疗疾病吗？中医从4个方面来为你解答
         * subTitle : 我们通常认为，食物是为人体提供生长发育的物质。 中医 饮食疗法又称食疗或食治，即利用食物来影响机...
         * imageUrl : http://www.zyzydq.com/uploads/allimg/181213/12-1Q213140354242.png
         * html : http://www.zyzydq.com/yaoshan/shiliao/23101.html
         * sortType : 食疗
         * lastTime : 2018-12-13
         */

        private String title;
        private String subTitle;
        private String imageUrl;
        private String html;
        private String sortType;
        private String lastTime;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getSortType() {
            return sortType;
        }

        public void setSortType(String sortType) {
            this.sortType = sortType;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }
    }
}
