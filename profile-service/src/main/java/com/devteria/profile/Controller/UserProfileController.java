package com.devteria.profile.Controller;

import com.devteria.profile.Service.UserProfileService;
import com.devteria.profile.dto.Request.ProfileCreationRequest;
import com.devteria.profile.dto.Response.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/users/{profileId}")
    public UserProfileResponse getProfile(@PathVariable String profileId){
        return userProfileService.getProfile(profileId);
    }
}
