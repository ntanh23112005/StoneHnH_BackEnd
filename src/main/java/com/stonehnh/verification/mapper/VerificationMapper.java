package com.stonehnh.verification.mapper;
import com.stonehnh.verification.entity.Verification;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface VerificationMapper {
    @Select("SELECT id, customer_id, authenication_code, created_time, is_verified FROM verifications")
    List<Verification> findAllVerifications();
}
