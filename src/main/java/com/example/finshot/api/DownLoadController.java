package com.example.finshot.api;

import com.example.finshot.application.DownLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/download")
@RequiredArgsConstructor
public class DownLoadController {

    private final DownLoadService downLoadService;

    @GetMapping("/csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        downLoadService.downLoadCsvEmployeeList(response);
    }

}
