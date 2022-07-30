package com.thesis.alumni.system.utils;
import com.thesis.alumni.system.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class CsvUtil {
    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<User> csvToUser(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<User> users = new ArrayList<User>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                User user = User.builder()
                        .id(csvRecord.get("id"))
                        .email(csvRecord.get("id") + "@vnu.edu.vn")
                        .gender(csvRecord.get("gender"))
                        .dateOfBirth(new Date(csvRecord.get("date_of_birth")))
                        .birthPlace(csvRecord.get("birth_place"))
                        .className(csvRecord.get("class_name"))
                        .status(csvRecord.get("status"))
                        .job(csvRecord.get("job"))
                        .jobHistory(csvRecord.get("job_history"))
                        .workplace(csvRecord.get("workplace"))
                        .salaryRange(csvRecord.get("salary_range"))
                        .build();
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}