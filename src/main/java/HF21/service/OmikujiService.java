package HF21.service;


import HF21.beans.OmikujiRecord;
import HF21.vo.OmikujiResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

public interface OmikujiService extends IService<OmikujiRecord> {

    OmikujiResult draw() throws IOException;

    OmikujiResult getOmikujiById(Integer id);



    /**
     * 根据主键 id 将 flag 翻转为 1（设为系上签）
     *
     * @param id 主键
     * @return 是否更新成功
     */
    boolean markAsLuckyById(Integer id);

    /**
     * 获取所有 flag = 1 的签的主键 id 列表
     *
     * @return 系上签的 id 集合
     */
    List<Integer> getLuckyOmikujiIds();


    OmikujiResult getMusubaredOmikujiById(Integer id);

}
