package com.ex.IdMaker;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Exception
 * @create 2021-07-17-14:20
 * @content
 */
@RestController
public class MakeIdController {

    public IdMaker idMaker;

    @SneakyThrows
    @RequestMapping(value = "/getId", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonModel getId(@RequestParam("type") String type) {
        if (idMaker == null) {
            return new JsonModel(0, "服务器未开启...", null);
        }
        String id = null;
        String msg = "生成成功";
        Integer code = 1;
        if ("DELAY".equals(type)) {
            id = idMaker.generateId(IdMaker.RemoveMethod.DELAY);
        } else if ("IMMEDIATELY".equals(type)) {
            id = idMaker.generateId(IdMaker.RemoveMethod.IMMEDIATELY);
        } else if ("NONE".equals(type)) {
            id = idMaker.generateId(IdMaker.RemoveMethod.NONE);
        } else {
            msg = "生成失败";
            code = 0;
        }
        return new JsonModel(code, msg, id);
    }

    @SneakyThrows
    @RequestMapping(value = "/openId", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonModel open(@RequestParam("nodeName")String nodeName) {
        try {
            System.out.println(idMaker);
            if (idMaker != null){
                return new JsonModel(0, "服务已开启", null);
            }
            idMaker = new IdMaker("/NameService/IdGen",nodeName);
            idMaker.start();
            return new JsonModel(1, "开启服务", null);
        }catch (Exception e){
            return new JsonModel(0, e.getMessage(), null);
        }
    }

    @SneakyThrows
    @RequestMapping(value = "/closeId", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonModel close() {
        if (idMaker == null) {
            return new JsonModel(0, "服务未开启", null);
        } else {
            idMaker.stop();
            idMaker = null;
            return new JsonModel(1, "服务关闭", null);
        }
    }
}
