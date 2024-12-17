package com.devteria.profile.Service;

import com.devteria.profile.Entity.UserProfile;
import com.devteria.profile.Repository.UserProfileRepository;
import com.devteria.profile.dto.Request.ProfileCreationRequest;
import com.devteria.profile.dto.Response.UserProfileResponse;
import com.devteria.profile.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(ProfileCreationRequest profileCreationRequest) {
        UserProfile userProfile = userProfileMapper.toUserProfile(profileCreationRequest);
        userProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile =userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException(("Profile not found")));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
