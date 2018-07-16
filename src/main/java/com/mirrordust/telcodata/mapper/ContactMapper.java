package com.mirrordust.telcodata.mapper;

import com.mirrordust.telcodata.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContactMapper {
    @Select("SELECT * FROM contact")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "type", column = "type")
    })
    List<Contact> getAll();

    @Select("SELECT * FROM contact WHERE type = #{type}")
    List<Contact> findByType(Integer type);
}
