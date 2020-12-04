package cn.hnvist.client.bean;

public class NewsBean {
    private int id;
    private String imgUrl;
    private String title;
    private String desc;
    private String pushTime;

    public NewsBean(int id, String imgUrl, String title, String desc, String pushTime) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
        this.desc = desc;
        this.pushTime = pushTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
