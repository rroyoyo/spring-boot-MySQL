package com.rroyoyo.cricketProfile.dao;

import java.util.List;
import com.rroyoyo.cricketProfile.model.Profile;


public interface CricketProfileDAO {

    public abstract int createProfile(Profile profile);
    public abstract List<Profile> getProfile();
    public abstract void updateProfile(Profile profile, String id);
    public abstract void deleteProfile(String id);
}
