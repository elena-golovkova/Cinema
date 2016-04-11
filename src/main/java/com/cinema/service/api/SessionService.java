package com.cinema.service.api;

import com.cinema.dto.SessionDTO;
import com.cinema.model.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SessionService extends Service<SessionDTO, Integer> {
    public List<SessionDTO> getALLSessionsWithAllData();

}
