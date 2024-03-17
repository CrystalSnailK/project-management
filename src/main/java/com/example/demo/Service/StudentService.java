package com.example.demo.Service;

import com.example.demo.Entities.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.IntStream;

public interface StudentService {
    public StudentInformationEntity getInformation(int id);
    public List<PersonalAwardRequestEntity> getAward(int id);
    public void addAward(int id, RecordPersonalAwardEntity awardEntity);
    public void updateInformation(int id, StudentUpdateInformationEntity studentsEntity);

    /**
     *
     * @param inputStream 文件输入流
     * @param suffix 文件后缀
     * @return
     * @throws IOException
     */
    public String saveImage(InputStream inputStream, String suffix) throws IOException;

    /**
     * 下载路径对应的图片
     * @param filename: 图片名称
     * @return 图片输出流
     */
    public InputStream getImage(String filename) throws IOException;
}
