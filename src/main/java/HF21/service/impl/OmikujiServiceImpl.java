package HF21.service.impl;

import HF21.beans.OmikujiContent;
import HF21.beans.OmikujiLuckyItem;
import HF21.beans.OmikujiRecord;
import HF21.config.OmikujiProps;
import HF21.mapper.OmikujiRecordMapper;
import HF21.service.OmikujiAiService;
import HF21.service.OmikujiService;
import HF21.utils.OmikujiUtil;
import HF21.utils.RandomPoolUtils;
import HF21.vo.OmikujiResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;


@Service
public class OmikujiServiceImpl
        extends ServiceImpl<OmikujiRecordMapper, OmikujiRecord>
        implements OmikujiService {


    private final OmikujiProps props;
    private final OmikujiAiService aiService;


    public OmikujiServiceImpl(OmikujiProps props, OmikujiAiService aiService) {
        this.props = props;
        this.aiService = aiService;
    }


    @Override
    public OmikujiResult draw() throws IOException {
        // 1. 按权重算法抽签位
        Map<OmikujiUtil.Rank, Integer> weights = props.getWeights();
        OmikujiUtil.Rank rank = OmikujiUtil.draw(weights);

        // 2. 用签位让 AI 生成日文签文
        OmikujiContent content = aiService.generateByRank(rank.getLabel());

        //3.随机生成幸运地点和食物
        OmikujiLuckyItem omikujiLuckyItem =
                new OmikujiLuckyItem(RandomPoolUtils.getRandomLuckyPlace(), RandomPoolUtils.getRandomNagoyaFood());


        // 4. 组装要插入数据库的实体
        OmikujiRecord record = new OmikujiRecord();
        record.setRank(rank.getLabel());
        record.setLove(content.getLove());
        record.setWish(content.getWish());
        record.setStudy(content.getStudy());
        record.setHealth(content.getHealth());
        record.setBusiness(content.getBusiness());
        record.setLuckyPlace(omikujiLuckyItem.getLuckyPlace());
        record.setNagoyaFood(omikujiLuckyItem.getNagoyaFood());

        // 存入数据库 and 获取id
        this.save(record);
        Integer id = record.getId();

        // 5. 返回结果
        return new OmikujiResult(id, rank.getLabel(), content, omikujiLuckyItem);
    }

    @Override
    public OmikujiResult getOmikujiById(Integer id) {
        // 1. 从数据库取一条记录
        OmikujiRecord record = this.getById(id);
        if (record == null) {
            return null;
        }

        // 2. 转成 OmikujiContent bean
        OmikujiContent content = new OmikujiContent(
                record.getWish(),
                record.getLove(),
                record.getStudy(),
                record.getBusiness(),
                record.getHealth()
        );

        // 3. 转成 OmikujiLuckyItem bean
        OmikujiLuckyItem luckyItem = new OmikujiLuckyItem(
                record.getLuckyPlace(),
                record.getNagoyaFood()
        );

        // 4. 组装最终 OmikujiResult
        OmikujiResult result = new OmikujiResult();
        result.setId(record.getId());
        result.setRank(record.getRank());
        result.setContent(content);
        result.setItems(luckyItem);

        return result;
    }
}
