package bbs.component.controller;

import bbs.utils.UploadFileUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {

    @PostMapping("/upload")
    public @ResponseBody
    String uploadImgHandler(@RequestPart("upload_img") MultipartFile file, HttpServletRequest request) {
        System.out.println(file);
        String fileName = file.getOriginalFilename();
        try {
            UploadFileUtils.saveFile(file.getInputStream(), fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject json = new JSONObject();
        List<String> fileNames = new ArrayList<>();
        fileNames.add(request.getContextPath() + "/upload/" + fileName);
        json.put("errno", 0);
        json.put("data", JSONArray.fromObject(fileNames));
        return json.toString();
    }
}
