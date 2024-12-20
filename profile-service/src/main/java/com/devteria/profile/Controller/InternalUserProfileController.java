package com.devteria.profile.Controller;

import com.devteria.profile.Service.UserProfileService;
import com.devteria.profile.dto.Request.ProfileCreationRequest;
import com.devteria.profile.dto.Response.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InternalUserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @PostMapping("/internal/users/")
    public UserProfileResponse createProfile(@RequestBody ProfileCreationRequest profileCreationRequest){
        return userProfileService.createProfile(profileCreationRequest);
    }
}
