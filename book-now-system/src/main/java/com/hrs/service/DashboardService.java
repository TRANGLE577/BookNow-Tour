package com.hrs.service;

import com.hrs.model.dto.DashboardDTO;

import javax.servlet.http.HttpSession;

public interface DashboardService {

    DashboardDTO showDashboard(HttpSession session);

}
