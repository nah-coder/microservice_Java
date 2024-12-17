package com.devteria.profile.mapper;

import com.devteria.profile.Entity.UserProfile;
import com.devteria.profile.dto.Request.ProfileCreationRequest;
import com.devteria.profile.dto.Response.UserProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest profileCreationRequest);
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
