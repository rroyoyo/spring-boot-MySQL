package com.rroyoyo.cricketProfile.controller;

import com.rroyoyo.cricketProfile.dao.CricketProfileDAO;
import com.rroyoyo.cricketProfile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CricketProfileController {

    @Autowired
    CricketProfileDAO cricketProfileDAO;
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ResponseEntity<Object> createProfile(@RequestBody Profile profile){
        cricketProfileDAO.createProfile(profile);
        return new ResponseEntity<>("Profile is created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<Object> getProfile() {
        return new ResponseEntity<>( cricketProfileDAO.getProfile(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProfile(@RequestBody Profile profile, @PathVariable("id") String id) {
        cricketProfileDAO.updateProfile(profile, id);
        return new ResponseEntity<>("Profile is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProfile(@PathVariable("id") String id) {
        cricketProfileDAO.deleteProfile(id);
        return new ResponseEntity<>("Profile delete successfully", HttpStatus.OK);
    }

}
