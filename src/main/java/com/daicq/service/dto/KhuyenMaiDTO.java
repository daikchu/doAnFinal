package com.daicq.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the KhuyenMai entity.
 */
public class KhuyenMaiDTO implements Serializable {

    private String id;

    @NotNull
    private String tieuDe;

    private String noiDung;

    private String loaiApDung;

    private Instant timeStart;

    private Instant timeEnd;

    private Float mucKM;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLoaiApDung() {
        return loaiApDung;
    }

    public void setLoaiApDung(String loaiApDung) {
        this.loaiApDung = loaiApDung;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Float getMucKM() {
        return mucKM;
    }

    public void setMucKM(Float mucKM) {
        this.mucKM = mucKM;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KhuyenMaiDTO khuyenMaiDTO = (KhuyenMaiDTO) o;
        if (khuyenMaiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khuyenMaiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhuyenMaiDTO{" +
            "id=" + getId() +
            ", tieuDe='" + getTieuDe() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", loaiApDung='" + getLoaiApDung() + "'" +
            ", timeStart='" + getTimeStart() + "'" +
            ", timeEnd='" + getTimeEnd() + "'" +
            ", mucKM=" + getMucKM() +
            ", status=" + getStatus() +
            "}";
    }
}
