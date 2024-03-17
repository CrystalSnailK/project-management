package com.example.demo.DAO;

import com.example.demo.Entities.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface Teachers {
    @Select("select a.ID as id, t.tname as t_name, a.password, t.department, t.phone, t.email " +
            "from teacher t " +
            "inner join account a " +
            "on a.ID=t.TID")
    public List<TeachersInformationEntity> getAllTeachers();
    @Select("select a.ID as id, t.tname as t_name, a.password, t.department, t.phone, t.email " +
            "from teacher t " +
            "inner join account a " +
            "on t.TID=a.ID where TID = #{id}")
    public List<TeachersInformationEntity> getTeachersById(int id);
    @Update("update teacher set phone = #{phone}, email = #{email} where TID = #{TID}")
    public void updateTeacherPhone(int TID, int phone, String email);
    @Select("select TID from teacher where t_name=#{t_name}")
    public int getTIDByName(String t_name);
    @Select("select * from teacher where TID=#{TID}")
    public TeacherInformationEntity getInformation(int TID);
    @Update("update teacher set phone = #{phone}, email = #{email} where TID = #{TID}")
    public void updateInformation(Map<String, Object> map);
    @Select("select s.SID as id, s.stu_name, c.c_name, c.time, a.grade, c.sponsor as host, c.level " +
            "from award a " +
            "inner join competions c on c.CID=a.CID " +
            "inner join students s on s.SID=a.SID")
    public List<TeacherAwardsRequestEntity> getAwards();
    @Select("select s.SID as id, s.stu_name, c.c_name, c.time, a.grade, c.sponsor as host, c.level " +
            "from award a " +
            "inner join competions c on c.CID=a.CID " +
            "inner join students s on s.SID=a.SID " +
            "where a.TID=#{TID}")
    public List<TeacherAwardsRequestEntity> getAward(int TID);

    @Select("SELECT r.RID, r.rname, t.tname,t.TID, t.email, t.phone,  t.department, r.sname, r.time " +
            "FROM research r " +
            "INNER JOIN teacher t ON t.TID = r.TID " +
            "WHERE r.rname LIKE CONCAT('%', #{key}, '%')"
    )
    public List<TeacherAndResearchVO> getResearch(String key);

    @Select("SELECT r.RID, r.rname, t.tname,t.TID, t.email, t.phone,  t.department, r.sname, r.time " +
            "FROM research r " +
            "INNER JOIN teacher t ON t.TID = r.TID "
    )
    public List<TeacherAndResearchVO> getAllResearch();

    @Insert("INSERT INTO research(rname, fileLocation, TID, sname, time, remark) " +
            "VALUES(#{rname}, #{fileLocation}, #{TID}, #{sname}, #{time}, #{remark})")
    public Integer updateResearch(updateResearchVO updateResearchVO);

}
