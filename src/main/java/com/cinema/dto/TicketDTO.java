package com.cinema.dto;

import com.cinema.model.User;

public class TicketDTO {
    private Integer id;
    private int row;
    private int column;



    public TicketDTO() {
    }

    public TicketDTO(Integer id, int row, int column) {
        setId(id);
        setRow(row);
        setColumn(column);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                '}'+"\n";
    }
}
