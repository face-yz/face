package com.ylf.user.daoAPI;

import com.ylf.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
   int insertUser(User user);
}