package com.cinema.model;

import java.io.Serializable;
import java.util.List;

public class Hall implements Serializable{
    private Integer id;
    private String name;
    private Integer rowCount;
    private Integer columnCount;

    public Hall() {
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

    public Hall(Integer id, String name, Integer rowCount, Integer columnCount) {
        setId(id);
        setName(name);
        setRowCount(rowCount);
        setColumnCount(columnCount);
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
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rowCount=" + rowCount +
                ", columnCount=" + columnCount +
                '}';
    }
}
