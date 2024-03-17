package com.example.demo.Service;

import com.example.demo.Entities.*;

import java.util.List;

public interface ManagerService {
    public List<CompetitionRequestEntity> getAllCompetitions();
    public void addCompetition(SubmitCompetionEntity competition);
    public List<StudentsInformationEntity> getAllStudents();
    public StudentsInformationEntity getStudentsByID(int id);
    public List<TeachersInformationEntity> getAllTeachers();
    public List<TeachersInformationEntity> getTeachersByID(int id);

    public List<TeacherAndResearchVO> getResearch(String key);
}
