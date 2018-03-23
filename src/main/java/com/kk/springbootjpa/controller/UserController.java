package com.kk.springbootjpa.controller;

import com.kk.springbootjpa.dao.UserRepository;
import com.kk.springbootjpa.domain.User;
import com.kk.springbootjpa.param.UserParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
        //redirect："redirect:/list";  直接定向到对应的mapping的url上
        //  return "user/userAdd";  return 到对应的界面元素上
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

     @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public String add(@Valid UserParam userParam, BindingResult result, ModelMap model){
        String erroMessage = "";
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError objectError:list){
                erroMessage = erroMessage + objectError.getCode()+"-"+objectError.getDefaultMessage();
            }
            model.addAttribute("erroMessage",erroMessage);
            return "user/userAdd";
        }
         User u = userRepository.findByUserName(userParam.getUserName());
         if(u != null ){
             model.addAttribute("erroMessage","用户已存在!");
             return "user/userAdd";
         }
         User user = new User();
         BeanUtils.copyProperties(userParam,user);//将前面对象的数据拷贝进后面的对象中
         user.setRegTime(new Date());
         userRepository.save(user);
         return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user=userRepository.findById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> users=userRepository.findList(pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result,ModelMap model) {
        String errorMsg="";
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg=errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }

        User user=new User();
        BeanUtils.copyProperties(userParam,user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }
}
