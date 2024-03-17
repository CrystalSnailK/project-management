package com.example.demo.Controller;

import com.example.demo.DAO.Research;
import com.example.demo.Service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class DownloadFile {
    @Autowired
    private Research research;

    @Autowired
    private TeachersService teachersService;
    @GetMapping("/download/byfilename")
    public void getImage(@RequestParam("filename") String filename, HttpServletResponse httpServletResponse) {
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        if (suffix.equals("zip")){
            httpServletResponse.setContentType("application/zip");
        } else if (suffix.equals("7z")) {
            httpServletResponse.setContentType("application/x-7z-compressed");
        }
        httpServletResponse.setHeader("Content-Disposition",
                "attachment;filename=" + filename);
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
    @GetMapping("/download/byrid")
    public void getImage(@RequestParam("rid") Integer id, HttpServletResponse httpServletResponse) {
        this.getImage(research.getResearchByRID(id).getFileLocation(), httpServletResponse);
    }
}
