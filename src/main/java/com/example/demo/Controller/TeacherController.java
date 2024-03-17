package com.example.demo.Controller;

import com.example.demo.Entities.*;
import com.example.demo.Service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeachersService teachersService;

    // 查询老师账户信息
    @GetMapping("/information")
    public TeacherInformationEntity getInformation() {
        Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return teachersService.getInformation(id);
    }
    // 更新老师账户信息
    @PostMapping("/information")
    public void updateInformation(@RequestBody TeacherUpdateInformationEntity teacherUpdateInformationEntity) {
        Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        teachersService.updateInformation(id, teacherUpdateInformationEntity);
    }
    // 查询所有的获奖名单
    @GetMapping("/awards")
    public BaseEntity getAllAwards(){
        return new BaseEntity(0, "", teachersService.getAwards().size(), teachersService.getAwards());
    }
    // 查询老师辅导的比赛名单
    @GetMapping("/award")
    public BaseEntity getAward(){
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TeacherAwardsRequestEntity> ts = teachersService.getAward(id);
        return new BaseEntity(0, "", ts.size(), ts);
    }
    @GetMapping("/awardbyid")
    public BaseEntity getAwardByID(@RequestParam int id){
        return new BaseEntity(0, "", 1, teachersService.getAwardByID1(id));
    }

    @GetMapping("/research")
    public TableVO getResearch(){
        Integer TID = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return TableVO.ok(teachersService.getResearch(TID).toArray());
    }
    @PostMapping("/research/file")
    public BaseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String var = file.getOriginalFilename();
        String[] var2 = var.split("\\.");
        String src = teachersService.saveFile(file.getInputStream(), var2[var2.length-1]);
        return new BaseEntity(0, "", 1, src);
    }
    @GetMapping("/research/file")
    public void getImage(@RequestParam("filename") String filename, HttpServletResponse httpServletResponse) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        if (suffix.equals("xlsx") || suffix.equals("xls")){
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }
        try (InputStream inputStream = teachersService.getFile(filename)){
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

    @PostMapping("/research/update")
    public Integer updateResearch(@RequestBody updateResearchVO updateResearchVO){
        updateResearchVO.setTID((Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        updateResearchVO.setTime(new Date());
        System.out.println("updateResearchVO:" + updateResearchVO.toString());
        System.out.println(updateResearchVO.toString());
        return teachersService.updateResearch(updateResearchVO);
    }

    @GetMapping("/research/delete")
    public Integer deleteResearch(@RequestParam("rid") int rid){
        return teachersService.deleteResearch(rid);
    }
    @GetMapping("/research/find")
    public BaseEntity findResearchVO(@RequestParam("rid") Integer rid){
        System.out.println("rid:" + rid);
        return new BaseEntity(0, "", teachersService.getResearchByRID(rid).size(), teachersService.getResearchByRID(rid));
    }
    @GetMapping("/research/findRname")
    public BaseEntity findByRname(@RequestParam("rname") String rname){
        System.out.println("rname" + rname);
        return new BaseEntity(0, "", teachersService.getResearchByRname(rname).size(), teachersService.getResearchByRname(rname));
    }
}
