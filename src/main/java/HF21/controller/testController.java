package HF21.controller;


import HF21.handler.WallWsHandler;
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


    private final WallWsHandler wallWsHandler;

    public testController(WallWsHandler wallWsHandler) {
        this.wallWsHandler = wallWsHandler;
    }

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
     * 根据 id 将签标记为「系上签」 + 广播给墙
     */
    @PostMapping("/musubu/{id}")
    public HttpResult<Void> musubu(@PathVariable Integer id) {

        boolean success = omikujiService.markAsLuckyById(id);
        if (!success) {
            return HttpResult.error("error");
        }

        // 推送给墙：告诉它新增了一条已结签
        wallWsHandler.broadcastJson("{\"type\":\"MUSUBU\",\"id\":" + id + ",\"latest\":true}");

        System.out.println("hello,world");

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




    @GetMapping("/musubu/{id}")
    public HttpResult<OmikujiResult> getMusubaredOmikuji(@PathVariable Integer id) {

        OmikujiResult result = omikujiService.getMusubaredOmikujiById(id);

        if (result == null) {
            return HttpResult.error("NOT_MUSUBARED");
        }

        return HttpResult.success("success", result);
    }


}
