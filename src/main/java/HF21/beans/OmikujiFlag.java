package HF21.beans;

import org.springframework.stereotype.Component;

@Component
public class OmikujiFlag {

    /**
     * 是否为系上签
     * 0 = 否
     * 1 = 是
     */
    private int flag;

    public OmikujiFlag() {
    }

    public OmikujiFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * 业务层可读性用
     */
    public boolean isLucky() {
        return flag == 1;
    }

    @Override
    public String toString() {
        return "OmikujiFlag{" +
                "flag=" + flag +
                '}';
    }
}