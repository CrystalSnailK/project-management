package com.example.demo.Service;

import com.example.demo.Entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TeachersService {
    public TeacherInformationEntity getInformation(int id);
    public void updateInformation(int id, TeacherUpdateInformationEntity teacherUpdateInformationEntity);
    public List<TeacherAwardsRequestEntity> getAwards();
    public List<TeacherAwardsRequestEntity> getAward(int id);
    public List<TeacherAwardsRequestEntity> getAwardByID1(int id);

    // 获取所有数据
    public List<ResearchDTO> getResearch(Integer TID);

    public String saveFile(InputStream ip, String suffix) throws IOException;
    public InputStream getFile(String filename) throws FileNotFoundException;
    public Integer updateResearch(updateResearchVO updateResearchVO);

    public Integer deleteResearch(Integer rid);
    public List<FindResearchVO> getResearchByRID(Integer rid);

    public List<FindResearchVO> getResearchByRname(String rname);
}
