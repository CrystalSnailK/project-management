package com.example.demo.Controller;

import com.example.demo.Entities.*;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    // 查询学生信息
    @GetMapping("/information")
    public StudentInformationEntity getInformation() {
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return studentService.getInformation(id);
    }
    // 更新学生信息
    @PostMapping("/information")
    public void updateInformation(@RequestBody StudentUpdateInformationEntity studentsEntity) {
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        studentService.updateInformation(id, studentsEntity);
    }

    // 查询个人获奖情况
    @GetMapping("/awards")
    public BaseEntity getAward(){
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new BaseEntity(0, "", studentService.getAward(id).size(), studentService.getAward(id));
    }
    // 录入个人奖项
    @PostMapping("/awards")
    public void addAward(@RequestBody RecordPersonalAwardEntity awardEntity){
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        studentService.addAward(id,awardEntity);
    }

    /**
     * @description 上传图片
     * @param file 接收图片的字段名称
     * @return 图片路径
     */
    @ResponseBody
    @PostMapping("/image")
    public Test receiveImage(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("image");
        String var = file.getOriginalFilename();
        String[] var2 = var.split("\\.");
        String src = studentService.saveImage(file.getInputStream(), var2[var2.length-1]);
        return Test.ok(new DataTest(src));
    }

    /**
     *
     *
     * @param filename: 要读取的文件名称
     * @param httpServletResponse: 将返回的结果写入到其中
     */
    @GetMapping("/image")
    public void getImage(@RequestParam String filename, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("image/jpg");
        try (InputStream inputStream = studentService.getImage(filename)){
            if (inputStream != null){
                httpServletResponse.getOutputStream().write(inputStream.readAllBytes());
                httpServletResponse.flushBuffer();
            }else {
                httpServletResponse.reset();
                httpServletResponse.setContentType("text/plain");
                try {
                    httpServletResponse.getOutputStream().write("路径有误".getBytes(StandardCharsets.UTF_8));
                    httpServletResponse.flushBuffer();
                }catch (IOException e1){
                    System.out.println("e1:");
                    e1.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
