package com.example.demo.DAO;

import com.example.demo.Entities.CompetitionRequestEntity;
import com.example.demo.Entities.SubmitCompetionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Competitions {
    @Select("select cname as c_name, time c_time, level , host, comment from competions")
    public List<CompetitionRequestEntity> getAllCompetitions();
    @Select("select CID from competions where cname=#{c_name}")
    public int getCIDByName(String c_name);
    @Insert("insert into competions(cname, time, level, host, comment) values(#{name}, #{date}, #{level}, #{host})")
    public void addCompetition(SubmitCompetionEntity competition);

}
