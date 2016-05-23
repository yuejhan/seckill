package org.seckill.web;

import org.seckill.dto.Expore;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by cody on 2016/5/22.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list", seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Expore> exposer(@PathVariable("seckillId") Long seckillId, Model model) {
        SeckillResult<Expore> result = null;
        Expore expore = null;
        try {
            expore = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Expore>(true, expore);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new SeckillResult<Expore>(false, e.getMessage());
        }
        return result;
    }
    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,produces={"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId,
                                              @PathVariable("md5") String md5,
                                              @CookieValue(value = "killPhone",required = false) Long userPhone){
        if(userPhone == null){
            return new SeckillResult<SeckillExcution>(false,"not register");
        }
        SeckillExcution seckillExcution = null;
        try {
            seckillExcution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExcution>(true,seckillExcution);
        }catch (RepeatKillException e){
            seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.REPEAT_KILL);
        }catch (SeckillCloseException e){
            seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.END);
        }catch (SeckillException e){
            return new SeckillResult<SeckillExcution>(false,e.getMessage());
        }
        return new SeckillResult<SeckillExcution>(true,seckillExcution);
    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        return new SeckillResult<Long>(true,new Date().getTime());
    }
}
