package nkdroid.com.sharedpreferencedemo;

/**
 * Created by nirav kalola on 10/30/2014.
 */
public class BeanSampleList {

    private int id;
    private String title;
    private String subTitle;

    public BeanSampleList() {
        super();
    }

    public BeanSampleList(int id, String title, String subTitle) {
        super();
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BeanSampleList other = (BeanSampleList) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
