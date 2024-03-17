package com.example.demo.DAO;

import com.example.demo.Entities.FindResearchVO;
import com.example.demo.Entities.ResearchDTO;
import com.example.demo.Entities.ResearchPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Research {
    @Select("SELECT r.RID, r.rname, t.tname, t.department, r.sname, r.time " +
            "FROM research r " +
            "inner join teacher t " +
            "on t.TID = r.TID " +
            "WHERE r.TID = #{TID} "
    )
    public List<ResearchDTO> getResearch(Integer TID);

    @Delete("DELETE FROM research WHERE RID = #{RID}")
    public Integer deleteResearch(Integer rid);

    @Select("SELECT * FROM research where rid=#{rid}")
    public ResearchPO getResearchByRID(Integer rid);

    @Select("SELECT r.RID, r.rname, t.tname,t.TID, t.email, t.phone, t.department, r.sname,r.remark, r.time " +
            " FROM research r INNER JOIN teacher t " +
            " ON t.TID = r.TID ")
    public List<FindResearchVO> getFindResearchVO();

    @Select("SELECT r.RID, r.rname, t.tname,t.TID, t.email, t.phone, t.department, r.sname,r.remark, r.time " +
            " FROM research r INNER JOIN teacher t " +
            " ON t.TID = r.TID "+
            "WHERE r.RID=#{rid}"
        )
    public List<FindResearchVO> getFindResearchVOByRID(Integer rid);

    @Select("SELECT r.RID, r.rname, t.tname,t.TID, t.email, t.phone, t.department, r.sname,r.remark, r.time " +
            " FROM research r INNER JOIN teacher t " +
            " ON t.TID = r.TID "+
            "WHERE r.rname LIKE CONCAT('%', #{rname}, '%')"
    )
    public List<FindResearchVO> getFindResearchVOByRname(String rname);

}
