package com.example.simplespringboot.mapper;

import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.model.MRegisterResponse;
import com.example.simplespringboot.model.MUserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-27T10:59:32+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public MRegisterResponse toRegisTerResponse(User user) {
        if ( user == null ) {
            return null;
        }

        MRegisterResponse mRegisterResponse = new MRegisterResponse();

        mRegisterResponse.setEmail( user.getEmail() );
        mRegisterResponse.setName( user.getName() );

        return mRegisterResponse;
    }

    @Override
    public MUserProfile toUserProfile(User user) {
        if ( user == null ) {
            return null;
        }

        MUserProfile mUserProfile = new MUserProfile();

        mUserProfile.setName( user.getName() );
        mUserProfile.setEmail( user.getEmail() );

        return mUserProfile;
    }
}
