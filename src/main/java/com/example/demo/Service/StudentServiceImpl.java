package com.example.demo.Service;

import com.alibaba.fastjson.JSON;
import com.example.demo.DAO.Award;
import com.example.demo.DAO.Competitions;
import com.example.demo.DAO.Students;
import com.example.demo.DAO.Teachers;
import com.example.demo.Entities.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private Students students;
    @Autowired
    private Award award;
    @Autowired
    private Teachers teachers;
    @Autowired
    private Competitions competitions;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    // 图片存储位置
    @Value("${static.path}")
    String imagePath;

    @Override
    public StudentInformationEntity getInformation(int id) {
        return students.getStudentById(id);
    }
    @Override
    public void updateInformation(int id, StudentUpdateInformationEntity studentsEntity) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(studentsEntity), Map.class);
        map.put("id", id);
        students.updateStudent(map);
    }
    @Override
    public List<PersonalAwardRequestEntity> getAward(int id) {
        return award.getAwardBySID(id);
    }
    @Override
    public void addAward(int id, RecordPersonalAwardEntity awardEntity) {
        int CID = competitions.getCIDByName(awardEntity.getC_name());
        int TID = teachers.getTIDByName(awardEntity.getTea_name());
        award.addAward(id, CID, TID, awardEntity.getGrade(), awardEntity.getLocateImage());
    }

    @Override
    public String saveImage(InputStream inputStream, String suffix) throws IOException {
        // 检查位置是否存在
        File file = new File(new String((imagePath + simpleDateFormat.format(new Date()) + "." + suffix).getBytes(), StandardCharsets.UTF_8));
        if (!file.exists()){
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        try (inputStream; FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(inputStream.readAllBytes());
            // 将路径写入到数据库中。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    @Override
    public InputStream getImage(String filename) throws IOException {
        File file = new File(imagePath + filename);
        return file.exists() && file.isFile() ? new FileInputStream(file) : null;
    }
}

