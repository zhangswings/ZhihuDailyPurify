package io.github.izzyleung.zhihudailypurify.bean;

/**
 * Created by swings on 2015-12-15.
 */
public class DailyImage {
    private String text;
    private String img;

    @Override
    public String toString() {
        return "DailyImage{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
