package HF21.vo;


import HF21.beans.OmikujiContent;
import HF21.beans.OmikujiLuckyItem;
import org.springframework.stereotype.Component;

@Component
public class OmikujiResult {

    private Integer id;

    // 签位文本：大吉 / 吉 / 凶 ...
    private String rank;

    // 详细签文（愿望、恋爱、学业、工作、健康、旅行）
    private OmikujiContent content;

    //幸运地点和食物
    private OmikujiLuckyItem items;

    private String color ;

    public OmikujiResult(Integer id ,String rank, OmikujiContent content, OmikujiLuckyItem items,String color) {
        this.id = id;
        this.rank = rank;
        this.content = content;
        this.items = items;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OmikujiResult() {
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public OmikujiContent getContent() {
        return content;
    }

    public void setContent(OmikujiContent content) {
        this.content = content;
    }

    public OmikujiLuckyItem getItems() {
        return items;
    }

    public void setItems(OmikujiLuckyItem items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "OmikujiResult{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                ", content=" + content +
                ", items=" + items +
                ", color='" + color + '\'' +
                '}';
    }
}
