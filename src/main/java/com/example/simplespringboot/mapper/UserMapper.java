package com.example.simplespringboot.mapper;

import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.MRegisterResponse;
import com.example.simplespringboot.model.MUserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface UserMapper {
    MRegisterResponse toRegisTerResponse(User user);
    MUserProfile toUserProfile(User user);
}
