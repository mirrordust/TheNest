package com.mirrordust.telcodata.mapper;

import com.mirrordust.telcodata.entity.Holder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HolderMapper {
    @Select("SELECT * FROM holder")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "imei", column = "imei")
    })
    List<Holder> getAll();

    @Select("SELECT * FROM holder WHERE imei = #{imei}")
    Holder findByImei(@Param("imei") String imei);
}
