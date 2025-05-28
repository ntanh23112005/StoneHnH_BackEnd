package com.stonehnh.customer.mapper;
import com.stonehnh.customer.entity.Roles;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface RoleMapper {
    @Select("SELECT role_id, role_name FROM roles")
    List<Roles> findAllRoles();
}
