package com.df.service;
import com.df.mapper.UserMapper;
import com.df.pojo.User;
import com.df.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * @author MFine
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    public int insert(User record) {
        return userMapper.insert(record);
    }


    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    public int insertSelective(User record) {
        record.setPassword(MD5Util.crypt(record.getPassword()));
        return userMapper.insertSelective(record);
    }


    /**
     * Select by primary key user.
     *
     * @param id the id
     * @return the user
     */
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    public int updateByPrimaryKeySelective(User record) {
        record.setPassword(MD5Util.crypt(record.getPassword()));
        return userMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * @param name 用户名
     * @return 找到的用户名
     */
    public User findOneByName(@NonNull String name) {
        return userMapper.findOneByName(name);
    }

    /**
     * @param name 用户名
     * @return id
     */
    public Integer findOneIdByName(String name) {
        return userMapper.findOneIdByName(name);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<User> findAll() {
        return userMapper.findAll();
    }


}





