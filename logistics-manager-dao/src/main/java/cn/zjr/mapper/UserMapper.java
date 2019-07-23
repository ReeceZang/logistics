package cn.zjr.mapper;

import cn.zjr.pojo.Role;
import cn.zjr.pojo.User;
import cn.zjr.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	void insertUserAndRoleId(Integer userId, Integer roleId);

	void deleteByRoleRelationById(Integer userId);

	List<Role> queryRoleByUserId(Integer userId);

	List<User> queryUserByRoleName(String roleName);
}