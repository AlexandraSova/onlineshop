package ru.floyo.admin.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.floyo.admin.entity.City;
import ru.floyo.admin.service.ICityService;

import java.util.List;

@Controller
@RequestMapping("/admin/table/cities")
public class CityController {

    private ICityService cityService;
    public static final String baseURL = "/admin/table/cities";

    @Autowired
    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<City> items = cityService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/table/cities");
        modelAndView.addObject("citiesList", items);
        modelAndView.addObject("baseURL", baseURL);
        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getByID(@PathVariable("id") Integer id) {
        City item = cityService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/entity/city");
        modelAndView.addObject("city", item);
        modelAndView.addObject("baseURL", baseURL);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getEditPage(@PathVariable("id") Integer id) {
        City item = cityService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/table/editCity");
        modelAndView.addObject("city", item);
        modelAndView.addObject("baseURL", baseURL);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("city") City item) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + baseURL);
        cityService.edit(item);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/table/editCity");
        modelAndView.addObject("baseURL", baseURL);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("city") City item) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + baseURL);
        cityService.add(item);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + baseURL);
        City item = cityService.getById(id);
        cityService.delete(item);
        return modelAndView;
    }
}
