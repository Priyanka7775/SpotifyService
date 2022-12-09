package com.example.SpotifyService.service;

import com.example.SpotifyService.domain.Song;
import com.example.SpotifyService.domain.User;
import com.example.SpotifyService.exception.SongNotFoundException;
import com.example.SpotifyService.exception.UserAlreadyFoundException;
import com.example.SpotifyService.exception.UserNotFoundException;
import com.example.SpotifyService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) throws UserAlreadyFoundException {
        if(userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyFoundException();
        }
        return userRepository.insert(user);
    }

    @Override
    public User addTrackFromUser(Song song, String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user=userRepository.findByEmail(email);
        if(user.getSongList()==null){
            user.setSongList(Arrays.asList(song));
        }else {
            List<Song> songs=user.getSongList();
            songs.add(song);
            user.setSongList(songs);
        }
        return userRepository.save(user);
    }

    @Override
    public List<Song> getAllSongOfUser(String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(email).get().getSongList();
    }

    @Override
    public User deleteTrackFromUser(String email, int songId) throws UserNotFoundException, SongNotFoundException {
        boolean result=false;
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user=userRepository.findById(email).get();
        List<Song> songs=user.getSongList();
        result=songs.removeIf(x->x.getSongId()==songId);
        if(!result){
            throw new SongNotFoundException();
        }
        user.setSongList(songs);
        return userRepository.save(user);
    }
}
