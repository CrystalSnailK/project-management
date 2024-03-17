package com.example.demo.DAO;

import com.example.demo.Entities.StudentInformationEntity;
import com.example.demo.Entities.StudentUpdateInformationEntity;
import com.example.demo.Entities.StudentsInformationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface Students {
    /**
     * 获取所有学生信息
     * @return
     */
    @Select("select a.ID as id, s.sname as stu_name, a.password, s.gender, s.department, s.grade, s.major, s.classnum as class_num, s.phone, s.email ")
    public List<StudentsInformationEntity> getAllStudents();

    /**
     * 通过ID获取学生信息
     * @param id
     * @return
     */
    @Select("select a.ID as id, s.sname as stu_name, a.password, s.gender, s.department, s.grade, s.major, s.classnum as class_num, s.phone, s.email" +
            "from students s inner join account a on a.ID=s.SID " +
            "where s.SID = #{id}")
    public StudentsInformationEntity getStudentsById(int id);

    /**
     * 新增学生信息
     * @param studentsEntity
     */
    @Insert("insert into students(SID,sname, gender, department, grade, major, classnum, phone, email) " +
            "values(#{SID},#{stu_name},#{gender},#{department},#{grade},#{major},#{class_num},#{phone},#{email})")
    public void addStudent(StudentsInformationEntity studentsEntity);

    @Select("select * from students where SID = #{id}")
    public StudentInformationEntity getStudentById(int id);
    @Update("update students set phone = #{phone}, email = #{email} from student where SID = #{id}")
    public void updateStudent(Map<String, Object> map);
}
