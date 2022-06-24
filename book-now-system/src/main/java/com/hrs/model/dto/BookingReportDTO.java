package com.hrs.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class BookingReportDTO {
    private String startDate;
    private String endDate;
    private long tourId;
}
