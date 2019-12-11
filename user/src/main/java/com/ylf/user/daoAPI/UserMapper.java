package com.ylf.user.daoAPI;

import com.ylf.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
   int insertUser(User user);
   List selectUserList();
   User selectUser(String uId);
   int updateUser(User user);
}