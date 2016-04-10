package com.cinema.dto;

import com.cinema.model.Ticket;

import java.util.List;


public class HallDTO {
    private Integer id;
    private String name;
    private Integer rowCount;
    private Integer columnCount;

    public HallDTO() {
    }

    public HallDTO(Integer id, String name, Integer rowCount, Integer columnCount) {
        setId(id);
        setName(name);
        setRowCount(rowCount);
        setColumnCount(columnCount);
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HallDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rowCount=" + rowCount +
                ", columnCount=" + columnCount +
                '}'+ "\n";
    }
}
