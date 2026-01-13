package HF21.controller;


import HF21.service.OmikujiService;
import HF21.vo.HttpResult;
import HF21.vo.OmikujiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(
        origins = "http://127.0.0.1:5500",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}
)
public class testController {

    @Resource
    private OmikujiService omikujiService;

    @PostMapping("/draw")
    public HttpResult<OmikujiResult> draw() {
        try {
            OmikujiResult result = omikujiService.draw();
            return HttpResult.success("success", result);
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResult.error("error" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("system error" + e.getMessage());
        }
    }


    @GetMapping("/get/{id}")
    public HttpResult<OmikujiResult> getOmikujiById(@PathVariable Integer id) {

        OmikujiResult omikuji =
                omikujiService.getOmikujiById(id);

        if(omikuji == null)
            return HttpResult.error("指定したおみくじは見つかりませんでした");

        return HttpResult.success("success",omikuji);

    }


    /**
     * 根据 id 将签标记为「系上签」
     */
    @PostMapping("/musubu/{id}")
    public HttpResult<Void> markAsLucky(@PathVariable Integer id) {

        boolean success = omikujiService.markAsLuckyById(id);

        if (!success) {
            return HttpResult.error("error");
        }

        return HttpResult.success("結ばれた", null);
    }

    /**
     * 获取所有「系上签」的 id
     */
    @GetMapping("/musubu/ids")
    public HttpResult<List<Integer>> getLuckyOmikujiIds() {

        List<Integer> ids = omikujiService.getLuckyOmikujiIds();

        return HttpResult.success("success", ids);
    }
}
