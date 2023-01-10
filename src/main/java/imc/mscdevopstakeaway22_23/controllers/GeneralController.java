package imc.mscdevopstakeaway22_23.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import imc.mscdevopstakeaway22_23.DTO.ItemDTO;
import imc.mscdevopstakeaway22_23.Form.AddItemForm;

import imc.mscdevopstakeaway22_23.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class GeneralController {


    private ItemRepository itemRepo;

    @Autowired
    public GeneralController(ItemRepository pRepo) {
        itemRepo = pRepo;
    }



    @RequestMapping(path="/Home")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home");
        return mav;
    }


    @RequestMapping(path="/Menu")
    public ModelAndView menu(){
        ModelAndView mav = new ModelAndView();
        List<ItemDTO> menu = new ArrayList();
        menu = itemRepo.getAllItems();
        mav.addObject("menu", menu);
        mav.setViewName("Menu");
        return mav;
    }

    @RequestMapping(path="/Admin/AddItem", method = RequestMethod.GET)
    public ModelAndView A_AddItemGET(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Admin/AddItem");
        return mav;
    }

    @RequestMapping(path="/Admin/AddItem", method = RequestMethod.POST)
    public ModelAndView A_AddItemPOST(AddItemForm item, BindingResult br){
        ModelAndView mav;
        if (br.hasErrors()) {
            System.out.println(br.getFieldErrors().toString());
            mav = A_AddItemGET();
        } else {
            boolean success = itemRepo.addItem(item);
            System.out.println(success);
            mav = menu();
        }
        System.out.println(item.getName());
        return mav;
    }
}
