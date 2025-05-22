package com.stonehnh.mapper;
import com.stonehnh.entity.Roles;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface RoleMapper {
    @Select("SELECT role_id, role_name FROM roles")
    List<Roles> findAllRoles();
}
