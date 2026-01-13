package HF21.beans;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Component;

@Component
@TableName("omikuji_t")
public class OmikujiRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String rank;

    private String love;
    private String wish;
    private String study;
    private String health;
    private String business;

    @TableField("lucky_place")
    private String luckyPlace;

    @TableField("nagoya_food")
    private String nagoyaFood;

    @TableField("flag")
    private Integer flag;

    public OmikujiRecord(Integer id, String rank, String love, String wish, String study, String health, String business, String luckyPlace, String nagoyaFood, Integer flag) {
        this.id = id;
        this.rank = rank;
        this.love = love;
        this.wish = wish;
        this.study = study;
        this.health = health;
        this.business = business;
        this.luckyPlace = luckyPlace;
        this.nagoyaFood = nagoyaFood;
        this.flag = flag;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public OmikujiRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getLuckyPlace() {
        return luckyPlace;
    }

    public void setLuckyPlace(String luckyPlace) {
        this.luckyPlace = luckyPlace;
    }

    public String getNagoyaFood() {
        return nagoyaFood;
    }

    public void setNagoyaFood(String nagoyaFood) {
        this.nagoyaFood = nagoyaFood;
    }


    @Override
    public String toString() {
        return "OmikujiRecord{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                ", love='" + love + '\'' +
                ", wish='" + wish + '\'' +
                ", study='" + study + '\'' +
                ", health='" + health + '\'' +
                ", business='" + business + '\'' +
                ", luckyPlace='" + luckyPlace + '\'' +
                ", nagoyaFood='" + nagoyaFood + '\'' +
                ", flag=" + flag +
                '}';
    }
}
