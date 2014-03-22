package com.byelex.newsparser.web;

import DBManager.Get;
import Models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author Taldykin V.S.
 * @version 1.00 31.01.14 13:40
 */
@Controller
@RequestMapping("/settings")
public class ProfileSettingsController {

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/")
    public String mainSettingsController(Map<String, Object> map) {
        map.put("profile", new Profile());
        map.put("profileList", Get.listProfiles());
        if(map.get("currentProfile") == null) {
            map.put("currentProfile"
                    , Get.getProfile(1L));
            map.put("templateText"
                    , Get.getProfile(1L).getText());
        }
        return "profilesSettings";
    }

    @RequestMapping(value = "/profile/{profileID}", method = RequestMethod.GET)
    public String getProfile(RedirectAttributes redirectAttributes
            , @PathVariable("profileID") int profileID) {
        redirectAttributes.addFlashAttribute("currentProfile"
                , Get.getProfile(Long.valueOf(profileID)));
        redirectAttributes.addFlashAttribute("templateText", Get.getProfile(Long.valueOf(profileID)).getText());
        return "redirect:/settings/";
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addTemplateGet() {
        ModelAndView modelAndView = new ModelAndView("add-profile-form");
        modelAndView.addObject("profile", new Profile());
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTemplatePost(@ModelAttribute Profile profile) {
        Get.addProfile(profile);
        return "redirect:/settings/";
    }

    @RequestMapping(value = "/edit/{profileID}", method = RequestMethod.GET)
     public ModelAndView editTemplateGet(@PathVariable("profileID") Long profileID) {
        ModelAndView modelAndView = new ModelAndView("edit-profile-form");
        Profile profile = Get.getProfile(Long.valueOf(profileID));
        modelAndView.addObject(profile);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{profileID}", method = RequestMethod.POST)
    public String editTemplatePost(@ModelAttribute Profile profile
            , @PathVariable("profileID") Long profileID) {
        Get.updateProfile(profile, profileID);
        return "redirect:/settings/";
    }

    @RequestMapping(value = "/delete/{profileID}"
            , method = RequestMethod.GET)
    public String deleteTemplate(@PathVariable("profileID") Long profileID) {
        Get.deleteProfile(profileID);
        return "redirect:/settings/";
    }
}

