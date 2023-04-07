package org.stepacademy.swm_diplom_mvc.controllers.restcontrollers.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.stepacademy.swm_diplom_mvc.model.dao.activity.activity.DBServiceActivity;
import org.stepacademy.swm_diplom_mvc.model.dao.customer.profile.DBServiceProfile;
import org.stepacademy.swm_diplom_mvc.model.dao.location.city.DBServiceCity;
import org.stepacademy.swm_diplom_mvc.model.entities.activity.activity.Activity;
import org.stepacademy.swm_diplom_mvc.model.entities.customer.profile.Profile;



@RestController
@RequestMapping(path = "/profile")
public class ProfileActivityUpdateController {
    @Autowired
    DBServiceActivity activityService;
    @Autowired
    DBServiceProfile profileService;
    @Autowired
    DBServiceCity cityService;


    @GetMapping("/activity/add")
    public Activity add(@RequestParam int profileId, @RequestParam String tag) {
        Activity activity = activityService.findByName(tag);
        Profile profile = profileService.findById(profileId).get();
        profile.getActivityTags().add(activity);
        profileService.save(profile);
        return activity;
    }

    @Transactional
    @GetMapping("/activity/delete")
    public String delete(@RequestParam int profileId, @RequestParam int tagId) {
        Activity activity = activityService.findById(tagId).get();
        String tag = activity.getName();
        Profile profile = profileService.findById(profileId).orElse(null);
        assert profile != null;
        profile.getActivityTags().remove(activity);
        return tag;
    }
}
