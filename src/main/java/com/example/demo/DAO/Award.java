package com.example.demo.DAO;

import com.example.demo.Entities.PersonalAwardRequestEntity;
import com.example.demo.Entities.TeacherAwardsRequestEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Award {
    /**
     * 通过学生ID来获取该学生获取的所有奖项
     * @param SID: 学生ID
     * @return
     */
    @Select("select c.c_name, c.time as c_time, a.grade, c.sponsor as host, c.level " +
            "from award a inner join competions c on c.CID=a.CID " +
            "where a.SID=#{SID}")
    public List<PersonalAwardRequestEntity> getAwardBySID(int SID);

    /**
     * 添加新的奖项
     * @param SID
     * @param CID
     * @param TID
     * @param grade
     */
    @Insert("insert into award(SID, CID, TID, grade, imgLocation) values(#{SID},#{CID},#{TID},#{grade}, {#locationImg})")
    public void addAward(int SID, int CID, int TID, String grade, String locationImg);

    /**
     *
     * @param id
     * @return
     */
    @Select("select s.SID as id, s.stu_name, c.c_name, c.time, a.grade, c.sponsor as host, c.level " +
            "from award a inner join students s on a.SID=s.SID inner join competions c on c.CID=a.CID " +
            "where s.SID={id}")
    public List<TeacherAwardsRequestEntity> getAwardBySID1(int id);
}
