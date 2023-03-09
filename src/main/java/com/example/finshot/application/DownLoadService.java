package com.example.finshot.application;

import com.example.finshot.api.request.EmployeeCsvDownloadRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static com.example.finshot.global.constants.FinShotConstant.*;

@Service
public class DownLoadService {

    public void downloadCsv(HttpServletResponse response, EmployeeCsvDownloadRequestDto requestDto) throws IOException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"employeeList.csv\"");

        Writer writer = new OutputStreamWriter(response.getOutputStream());
        makeCsvForm(requestDto, writer);
        writer.flush();
    }

    private void makeCsvForm(EmployeeCsvDownloadRequestDto requestDto, Writer writer) throws IOException {
        String[] idList = requestDto.getId().split(COMMA);
        String[] emailList = requestDto.getEmail().split(COMMA);
        String[] nameList = requestDto.getName().split(COMMA);
        String[] phoneList = requestDto.getPhone().split(COMMA);
        String[] positionList = requestDto.getPosition().split(COMMA);

        writer.append(CSV_HEADER).append(LF);
        for (int index = 0; index < idList.length; index++) {
            writer.append(idList[index]).append(COMMA)
                    .append(positionList[index]).append(COMMA)
                    .append(nameList[index]).append(COMMA)
                    .append(phoneList[index]).append(COMMA)
                    .append(emailList[index]).append(LF);
        }
    }
}
