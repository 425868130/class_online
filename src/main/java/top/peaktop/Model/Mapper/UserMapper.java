package top.peaktop.Model.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.peaktop.Model.User;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*通过账号查找用户*/
    Long getIdByAccount(String account);

    /*通过账号获取用户对象*/
    User selectByAccount(String account);

    /*mybatis多参数传递*/
    User userLoginCheck(@Param("account") String account, @Param("password") String password);
}