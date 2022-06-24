package com.hrs.service.impl;

import com.hrs.model.dto.DashboardDTO;
import com.hrs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    AccountService accountService;

    @Autowired
    TourService tourService;

    @Autowired
    BookingService bookingService;

    @Autowired
    LocationService locationService;

    @Override
    public DashboardDTO showDashboard(HttpSession session) {
        int totalTour = 0;
        int totalAccount = 0;
        int totalBooking = 0;
        int totalLocation = 0;

        try {
            String token = "";
            if (session.getAttribute("token") != null) {
                token = session.getAttribute("token").toString();
                totalTour = tourService.getAll().size();
                totalAccount = accountService.getList(token).size();
                totalBooking = bookingService.getList(token).size();
                totalLocation = locationService.getList(token).size();
            }
        } catch (Exception ex) {

        }

        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTotalTour(totalTour);
        dashboard.setTotalAccount(totalAccount);
        dashboard.setTotalBooking(totalBooking);
        dashboard.setTotalLocation(totalLocation);

        return dashboard;
    }

}
