package com.example.finshot.application;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

@Service
public class DownLoadService {
    public void downLoadCsvEmployeeList(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"data.csv\"");
        Writer writer = new OutputStreamWriter(response.getOutputStream());
        writer.append("직원번호 직급 이름 전화번호 이메일");

        writer.flush();
    }
}
