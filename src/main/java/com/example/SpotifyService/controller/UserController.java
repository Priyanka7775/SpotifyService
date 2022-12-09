package com.example.SpotifyService.controller;

import com.example.SpotifyService.domain.Song;
import com.example.SpotifyService.domain.User;
import com.example.SpotifyService.exception.SongNotFoundException;
import com.example.SpotifyService.exception.UserAlreadyFoundException;
import com.example.SpotifyService.exception.UserNotFoundException;
import com.example.SpotifyService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userMusic/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyFoundException {
        ResponseEntity responseEntity=null;
        try{
            user.setSongList(new ArrayList<>());
            responseEntity=new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyFoundException e){
            throw new UserAlreadyFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/song/{email}")
    public ResponseEntity<?> addTrackFromUser( @RequestBody Song song,@PathVariable String email)throws UserNotFoundException {
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.addTrackFromUser(song, email),HttpStatus.OK);
        }catch (UserNotFoundException e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/song/songs")
    public ResponseEntity<?>getProductForUser(@RequestBody User user)throws UserNotFoundException{
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.getAllSongOfUser(user.getEmail()),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @DeleteMapping("/song/delete/{songId}/{email}")
    public ResponseEntity<?> deleteTrackFromUser(@PathVariable(value = "songId") int songId,@PathVariable(value = "email") String email)throws UserNotFoundException, SongNotFoundException {
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.deleteTrackFromUser(email,songId),HttpStatus.OK);
        }catch (SongNotFoundException e){
            throw new SongNotFoundException();
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
