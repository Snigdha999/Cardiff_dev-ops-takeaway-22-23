package imc.mscdevopstakeaway22_23.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @RequestMapping(path="/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
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


    @RequestMapping(path="/Admin/DeleteItem")
    public ModelAndView getDeleteItem(){
        ModelAndView mav = new ModelAndView();
        List<ItemDTO> menu = new ArrayList();
        menu = itemRepo.getAllItems();
        mav.addObject("menu", menu);
        mav.setViewName("Admin/DeleteItems");
        return mav;
    }

    //    I know this is not very restfull.
    @RequestMapping(path = "/Admin/DeleteItem/{id}", method = RequestMethod.POST)
    public RedirectView deleteItem(@PathVariable Optional<String> id) {
        int rowsAffected = 0;
        System.out.println("--IMC--ItemController.DeleteItem -- id = "+id);
        if(id.isPresent()) {
            rowsAffected = itemRepo.deleteItem(Integer.parseInt(id.get()) );
            System.out.println("--IMC--ItemController.DeleteItem -- rows affected = "+ rowsAffected);
        }
        return new RedirectView("/Admin/DeleteItem");
    }

}
