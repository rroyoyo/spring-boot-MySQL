package com.rroyoyo.cricketProfile.dao;

import com.rroyoyo.cricketProfile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class CricketProfileDAOImpl implements CricketProfileDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int createProfile(Profile profile){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO PROFILE(NAME, RUNS, WICKETS) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getRuns());
            preparedStatement.setString(3, profile.getWickets());
            return preparedStatement;},
            keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Profile> getProfile() {
        List<Profile> profileList = new ArrayList<>();
        Collection < Map < String, Object > > rows = null;
        rows = jdbcTemplate.queryForList("SELECT NAME, RUNS, WICKETS, ID FROM PROFILE");

        rows.stream().map((row) -> {
            Profile p = new Profile();
            p.setName((String) row.get("NAME"));
            p.setRuns((String) row.get("RUNS"));
            p.setWickets((String) row.get("WICKETS"));
            p.setProfileid(String.valueOf(row.get("ID")));
            return p;}
        ).forEach((ss) -> {
            profileList.add(ss);
        });

        return profileList;
    }

    @Override
    public void updateProfile(Profile profile, String id){
        jdbcTemplate.update("UPDATE PROFILE SET NAME=?, RUNS=?, WICKETS=? WHERE ID=?", new Object[]{profile.getName(), profile.getRuns(), profile.getWickets(), id});
    }

    @Override
    public void deleteProfile(String id) {
        jdbcTemplate.update("DELETE FROM PROFILE WHERE ID = ?", new Object[]{id});
    }
}
