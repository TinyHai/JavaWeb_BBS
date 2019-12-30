package bbs.dao.mapper;

import bbs.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    @Select("select * from user_simple_view")
    List<User> getSimpleUsers();

    @Select("select * from account_view")
    List<User> getAccount();

    @Select("select pwd_changed from user where id=#{id}")
    int pwdChanged(@Param("id") long id);

    @Select("select * from account_view where user_name=#{userName}")
    @Results(value = {
            @Result(
                    id = true,
                    property = "id",
                    column = "id"
            )
    })
    User getAccountByName(@Param("userName") String userName);

    @Insert("insert into user(user_name, password, email) values(#{userName}, #{password}, #{email})")
    void insertUser(
            @Param("userName") String userName,
            @Param("password") String password,
            @Param("email")String email
    );

    @Select("select * from user where email=#{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") long id);

    @Select("select * from user where user_name=#{userName}")
    @Results(value = {
            @Result(
                    id = true,
                    property = "id",
                    column = "id"
            )
    })
    User getUserByName(@Param("userName") String userName);
}
