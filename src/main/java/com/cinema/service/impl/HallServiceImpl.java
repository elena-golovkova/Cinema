package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.HallDAO;
import com.cinema.dao.impl.HallDAOBD;
import com.cinema.dao.impl.HallDAOImpl;
import com.cinema.dao.storage.Configuration;
import com.cinema.dto.HallDTO;
import com.cinema.model.Hall;
import com.cinema.service.api.HallService;

import java.util.LinkedList;
import java.util.List;

public final class HallServiceImpl implements HallService {
    private static HallServiceImpl hallService;
    boolean isDB = Boolean.valueOf(Configuration.getInstance().getProperty(Configuration.DB));//isInmemory

    public synchronized static HallServiceImpl getInstance() {
        if (hallService == null) {
            hallService = new HallServiceImpl();
        }
        return hallService;
    }

    private HallServiceImpl() {
    }

    @Override
    public List<HallDTO> getAll() {
        List<HallDTO> list = new LinkedList<>();
        if (!isDB) {
            HallDAO hallDAO = HallDAOImpl.getInstance();
            List<Hall> halls = hallDAO.getAll();
            List<HallDTO> hallDTOs = Transformer.listHallsToListHallsDto(halls);
            list = hallDTOs;
        } else{
            HallDAO hallDAO = HallDAOBD.getInstance();
            List<Hall> halls = hallDAO.getAll();
            List<HallDTO> hallDTOs = Transformer.listHallsToListHallsDto(halls);
            list = hallDTOs;
        }
        return list;
    }

    @Override
    public void create(HallDTO hallDTO) {
        if (!isDB) {
            HallDAO hallDAO = HallDAOImpl.getInstance();
            Hall hall = Transformer.hallDtoToHall(hallDTO);
            hallDAO.create(hall);
        }else {
            HallDAO hallDAO = HallDAOBD.getInstance();
            Hall hall = Transformer.hallDtoToHall(hallDTO);
            hallDAO.create(hall);
        }
    }

    @Override
    public HallDTO get(Integer id) {
        HallDTO hallDTO = null;
        if (!isDB) {
            HallDAO hallDAO = HallDAOImpl.getInstance();
            Hall hall = (Hall) hallDAO.get(id);
            hallDTO = Transformer.hallToHallDto(hall);
        }else{
            HallDAO hallDAO = HallDAOBD.getInstance();
            Hall hall = (Hall) hallDAO.get(id);
            hallDTO = Transformer.hallToHallDto(hall);
        }
        return hallDTO;
    }

    @Override
    public HallDTO update(HallDTO hallDTO) {
        HallDTO hallDTONew = null;
        if (!isDB) {
            HallDAO hallDAO = HallDAOImpl.getInstance();
            Hall hall = (Hall) hallDAO.update(Transformer.hallDtoToHall(hallDTO));
            hallDTONew = Transformer.hallToHallDto(hall);
        }else {
            HallDAO hallDAO = HallDAOBD.getInstance();
            Hall hall = (Hall) hallDAO.update(Transformer.hallDtoToHall(hallDTO));
            hallDTONew = Transformer.hallToHallDto(hall);
        }
        return hallDTONew;
    }

    @Override
    public void delete(Integer id) {
        if (!isDB) {
            HallDAO hallDAO = HallDAOImpl.getInstance();
            hallDAO.delete(id);
        }else {
            HallDAO hallDAO = HallDAOBD.getInstance();
            hallDAO.delete(id);
        }

    }
}
