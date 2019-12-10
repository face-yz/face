package com.ylf.user.daoAPI;

import com.ylf.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: leifeng.ye
 * @date: 2019-12-10
 * @desc:
 */
@Repository
public interface UserMapper {
    int insertUser(User user);
}
