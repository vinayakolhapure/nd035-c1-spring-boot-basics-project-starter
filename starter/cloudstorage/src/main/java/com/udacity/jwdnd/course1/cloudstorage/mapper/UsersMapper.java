package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper {

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE users.userid = #{id}")
    User findById(Long id);

    @Select("SELECT * FROM users WHERE users.username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO users (username, password, firstname, lastname) VALUES (#{user.username}, #{user.password}, #{user.firstName}, #{user.lastName})")
    Integer create(@Param("user") User user);
}