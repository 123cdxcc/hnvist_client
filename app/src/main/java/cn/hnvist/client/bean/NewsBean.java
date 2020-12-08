package cn.hnvist.client.bean;

public class NewsBean {
    private String id;
    private String imgUrl;
    private String title;
    private String desc;
    private String pushTime;
    private String lastTime;

    public NewsBean(String id, String imgUrl, String title, String desc, String pushTime, String lastTime) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
        this.desc = desc;
        this.pushTime = pushTime;
        this.lastTime = lastTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "id='" + id + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", pushTime='" + pushTime + '\'' +
                '}';
    }
}
