package com.stonehnh.homestay.mapper;

import com.stonehnh.homestay.entity.HomestayRule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomestayRulesMapper {
    /**
     * Lấy danh sách tất cả nội quy homestay.
     *
     * @return Danh sách các đối tượng HomestayRule.
     */
    @Select("SELECT id, homestay_id, rule_text, policy_text, created_at FROM homestay_rules")
    List<HomestayRule> findAllRules();

    /**
     * Lấy nội quy homestay theo ID.
     *
     * @param id ID của nội quy cần lấy.
     * @return Đối tượng HomestayRule nếu tồn tại, null nếu không.
     */
    @Select("SELECT id, homestay_id, rule_text, policy_text, created_at FROM homestay_rules WHERE id = #{id}")
    HomestayRule findRuleById(@Param("id") int id);

    /**
     * Lấy danh sách nội quy của một homestay theo mã homestay.
     *
     * @param homestayId Mã homestay cần lấy nội quy.
     * @return Danh sách các đối tượng HomestayRule.
     */
    @Select("SELECT id, homestay_id, rule_text, policy_text, created_at FROM homestay_rules WHERE homestay_id = #{homestayId}")
    HomestayRule findRulesByHomestayId(@Param("homestayId") String homestayId);

    /**
     * Thêm mới một nội quy cho homestay.
     *
     * @param rule Đối tượng HomestayRule chứa thông tin cần thêm.
     * @return Số dòng bị ảnh hưởng (thường là 1 nếu thành công).
     */
    @Insert("INSERT INTO homestay_rules (id, homestay_id, rule_text, policy_text, created_at) " +
            "VALUES (#{rule.id}, #{rule.homestayId}, #{rule.ruleText}, #{rule.policyText}, #{rule.createdAt})")
    int insertRule(@Param("rule") HomestayRule rule);

    /**
     * Cập nhật nội quy homestay theo ID.
     *
     * @param rule Đối tượng HomestayRule chứa thông tin cần cập nhật.
     * @return Số dòng bị ảnh hưởng.
     */
    @Update("UPDATE homestay_rules SET " +
            "homestay_id = #{rule.homestayId}, " +
            "rule_text = #{rule.ruleText}, " +
            "policy_text = #{rule.policyText}, " +
            "created_at = #{rule.createdAt} " +
            "WHERE id = #{rule.id}")
    int updateRule(@Param("rule") HomestayRule rule);

    /**
     * Xóa nội quy homestay theo ID.
     *
     * @param id ID của nội quy cần xóa.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM homestay_rules WHERE id = #{id}")
    int deleteRuleById(@Param("id") int id);

    /**
     * Xóa tất cả nội quy của một homestay theo mã homestay.
     *
     * @param homestayId Mã homestay cần xóa nội quy.
     * @return Số dòng bị ảnh hưởng.
     */
    @Delete("DELETE FROM homestay_rules WHERE homestay_id = #{homestayId}")
    int deleteRulesByHomestayId(@Param("homestayId") String homestayId);

    /**
     * Kiểm tra xem có tồn tại quy định (rule) trong bảng HomestayRules với id được cung cấp hay không.
     *
     * @param id ID của quy định cần kiểm tra.
     * @return true nếu tồn tại quy định với id tương ứng, ngược lại trả về false.
     */
    @Select("SELECT COUNT(*) > 0 FROM HomestayRules WHERE id = #{id}")
    boolean existsRuleById(int id);
}
