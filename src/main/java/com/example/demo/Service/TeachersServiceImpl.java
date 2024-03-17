package com.example.demo.Service;

import com.alibaba.fastjson.JSON;
import com.example.demo.DAO.Award;
import com.example.demo.DAO.Research;
import com.example.demo.DAO.Students;
import com.example.demo.DAO.Teachers;
import com.example.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TeachersServiceImpl implements TeachersService {
    @Autowired
    private Teachers teachers;
    @Autowired
    private Award award;
    @Autowired
    Research research;
    @Value("${static.path}")
    String imagePath;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    public TeacherInformationEntity getInformation(int id) {
        return teachers.getInformation(id);
    }
    @Override
    public void updateInformation(int id, TeacherUpdateInformationEntity teacherUpdateInformationEntity) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(teacherUpdateInformationEntity), Map.class);
        map.put("TID", id);
        teachers.updateInformation(map);
    }
    @Override
    public List<TeacherAwardsRequestEntity> getAwards() {
        return teachers.getAwards();
    }
    public List<TeacherAwardsRequestEntity> getAward(int id) {
        return teachers.getAward(id);
    }
    @Override
    public List<TeacherAwardsRequestEntity> getAwardByID1(int id){
        return award.getAwardBySID1(id);
    }

    @Override
    public List<ResearchDTO> getResearch(Integer TID){
        return research.getResearch(TID);
    }

    @Override
    public String saveFile(InputStream ip, String suffix) throws IOException {
        File file = new File(new String((imagePath + simpleDateFormat.format(new Date()) + "." + suffix).getBytes(), StandardCharsets.UTF_8));
        if (!file.exists()){
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        try (ip; FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(ip.readAllBytes());
            // 将路径写入到数据库中。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }
    @Override
    public InputStream getFile(String filename) throws FileNotFoundException {
        File file = new File(imagePath + filename);
        return file.exists() && file.isFile() ? new FileInputStream(file) : null;
    }
    public Integer updateResearch(updateResearchVO updateResearchVO){
        return teachers.updateResearch(updateResearchVO);
    }
    public Integer deleteResearch(Integer rid){
        return research.deleteResearch(rid);
    }
    @Override
    public List<FindResearchVO> getResearchByRID(Integer rid){
        if (rid == 0){
            return research.getFindResearchVO();
        }
        return research.getFindResearchVOByRID(rid);
    }

    @Override
    public List<FindResearchVO> getResearchByRname(String rname){
        return research.getFindResearchVOByRname(rname);
    }


}
