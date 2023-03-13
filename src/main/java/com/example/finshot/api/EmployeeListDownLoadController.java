package com.example.finshot.api;

import com.example.finshot.api.request.EmployeeCsvDownloadRequestDto;
import com.example.finshot.application.EmployeeListDownLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/download")
@RequiredArgsConstructor
public class EmployeeListDownLoadController {

    private final EmployeeListDownLoadService downLoadService;

    @PostMapping("/csv")
    public void downloadCsvTest(HttpServletResponse response, EmployeeCsvDownloadRequestDto csvDownloadRequestDto) throws IOException {
        downLoadService.downloadCsv(response, csvDownloadRequestDto);
    }

}
