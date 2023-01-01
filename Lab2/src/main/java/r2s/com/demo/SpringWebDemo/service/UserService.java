package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.response.UserResponseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {

    
    public List<UserResponseDTO> getListUser(){
        List<UserResponseDTO> userList= new ArrayList<>();
        UserResponseDTO user1= new UserResponseDTO(1,"thienthu1", "nguyen thu 1","111111","123456","Nu","thu@gmail.com",new Date(2001,3,2));
        UserResponseDTO user2= new UserResponseDTO(2,"thienthu2", "nguyen thu 2","111111","123456","Nu","thu@gmail.com",new Date(2020,3,2));
        userList.add(user1);
        userList.add(user2);
        return userList;
    }
}
