package com.stonehnh.customer.mapper;
import com.stonehnh.customer.entity.Roles;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface RoleMapper {
    /**
     * Lấy danh sách tất cả roles
     * @return Danh sách roles
     */
    @Select("SELECT role_id, role_name FROM roles")
    List<Roles> findAllRoles();

    /**
     * Tìm role theo ID
     * @param roleId Mã vai trò
     * @return Vai trò nếu tìm thấy, ngược lại trả về null
     */
    @Select("SELECT role_id, role_name FROM roles WHERE role_id = #{roleId}")
    Roles findRoleById(@Param("roleId") String roleId);

    /**
     * Thêm vai trò mới
     * @param role Đối tượng Roles
     * @return Số dòng bị ảnh hưởng
     */
    @Insert("INSERT INTO roles (role_id, role_name) VALUES (#{role.roleId}, #{role.roleName})")
    int insertRole(@Param("role") Roles role);

    /**
     * Cập nhật vai trò
     * @param role Đối tượng Roles đã cập nhật
     * @return Số dòng bị ảnh hưởng
     */
    @Update("UPDATE roles SET role_name = #{role.roleName} WHERE role_id = #{role.roleId}")
    int updateRole(@Param("role") Roles role);

    /**
     * Xoá vai trò theo ID
     * @param roleId Mã vai trò cần xoá
     * @return Số dòng bị ảnh hưởng
     */
    @Delete("DELETE FROM roles WHERE role_id = #{roleId}")
    int deleteRoleById(@Param("roleId") String roleId);

    /**
     * Kiểm tra role có tồn tại không theo ID
     * @param roleId Mã vai trò
     * @return true nếu tồn tại, ngược lại false
     */
    @Select("SELECT COUNT(*) > 0 FROM roles WHERE role_id = #{roleId}")
    boolean isExistedRoleById(@Param("roleId") String roleId);
}
