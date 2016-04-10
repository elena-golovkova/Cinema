package com.cinema.model;


public class Ticket {
    private Integer id;
    private Integer row;
    private Integer column;


    public Ticket() {
    }

    public Ticket(Integer id, int row, int column) {
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

    public Integer getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
