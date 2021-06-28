package com.mvc.service;

import com.mvc.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    public List<UserDto> user = new ArrayList<>();
    public int id=1;

    public void save(UserDto userDto){

        userDto.setId(id);
        id++;
        user.add(userDto);
    }

    public List<UserDto> list (){
        return user;
    }

    public UserDto findById(int id){
        UserDto userDto = new UserDto();
        for (UserDto userDto1 : user){
            if (userDto1.getId() == id)
                return userDto1;
        }
        return userDto;
    }

    public void update(UserDto userDto){
        user.add(userDto);
    }

}
