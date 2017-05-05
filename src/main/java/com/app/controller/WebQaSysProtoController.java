package com.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResultQAModelDto;
import com.app.form.IndexForm;
import com.app.service.WebQAExeService;

import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@EnableAutoConfiguration
@RequestMapping("/webqa")
public class WebQaSysProtoController 
{
    @Autowired
    WebQAExeService webQAExeService;
    
    @ModelAttribute
    IndexForm setUpIndexForm() {
        return new IndexForm();
    }
    
    
    private static final Logger logger = LoggerFactory.getLogger(WebQaSysProtoController.class);
    
    /**
     * Json テストデータの配列を返却する。
     */
    @RequestMapping(value = "getWebQAJsonData", method = RequestMethod.POST)
    @ResponseBody
    public String[] getWebQAJsonData(Locale locale, @Validated IndexForm indexForm
            , BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("hasErrorFlag", "1");
            return null;
        }
        
        IndexForm index = new IndexForm();
        BeanUtils.copyProperties(indexForm, index);
        
        // ログを出力する
        logger.info("call getWebQAData");
        String[] datas = {"test1", "test2", "test3"};
     
        return datas;
    }
    
    /**
     * テストデータの配列を返却する。
     * @throws Exception 
     */
    @RequestMapping(value = "getWebQAData", method = RequestMethod.POST)
    public String getWebQAData(Locale locale, @Validated IndexForm indexForm
            , BindingResult result, Model model) throws Exception {
        
        if (result.hasErrors()) {
            model.addAttribute("hasErrorFlag", "1");
            return inputArea(locale, model);
        }
        
        // ログを出力する
        logger.info("call getWebQAData");
        
        IndexForm index = new IndexForm();
        BeanUtils.copyProperties(indexForm, index);
        String[] question = new String[1];
        question[0] = index.getInputQueText();
        ResultQAModelDto resultQADto = webQAExeService.getWebQA(question);
        model.addAttribute("resultQADto", resultQADto);
     
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate );
        
        model.addAttribute("hasErrorFlag", "0");

        return "window/anserList";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Locale locale, IndexForm indexForm, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        
        ResultQAModelDto resultQADto = new ResultQAModelDto();
        model.addAttribute("resultQADto", resultQADto);
        model.addAttribute("serverTime", formattedDate );
        
        return "webQAindex";
    }
    
    private String inputArea(Locale locale, Model model) {
        logger.info("Welcome home ERROR! The client locale is {}.", locale);
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        
        ResultQAModelDto resultQADto = new ResultQAModelDto();
        model.addAttribute("resultQADto", resultQADto);
        model.addAttribute("serverTime", formattedDate );
        
        return "window/inputArea";
    }
}