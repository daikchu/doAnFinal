package com.daicq.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CTDH entity.
 */
public class CTDHDTO implements Serializable {

    private String id;

    private Long sachId;

    private Long soLuong;

    private Float thanhTien;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSachId() {
        return sachId;
    }

    public void setSachId(Long sachId) {
        this.sachId = sachId;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CTDHDTO cTDHDTO = (CTDHDTO) o;
        if (cTDHDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cTDHDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CTDHDTO{" +
            "id=" + getId() +
            ", sachId=" + getSachId() +
            ", soLuong=" + getSoLuong() +
            ", thanhTien=" + getThanhTien() +
            "}";
    }
}
