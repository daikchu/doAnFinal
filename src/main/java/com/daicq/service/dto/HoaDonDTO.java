package com.daicq.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the HoaDon entity.
 */
public class HoaDonDTO implements Serializable {

    private String id;

    private Long khachHangId;

    private String loaiKH;

    private Instant ngayLap;

    private String tenKHVangLai;

    private String addrKHVangLai;

    private String sdtKHVangLai;

    private Integer trangThaiNhan;

    private Integer trangThaiThanhToan;

    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(Long khachHangId) {
        this.khachHangId = khachHangId;
    }

    public String getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }

    public Instant getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Instant ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTenKHVangLai() {
        return tenKHVangLai;
    }

    public void setTenKHVangLai(String tenKHVangLai) {
        this.tenKHVangLai = tenKHVangLai;
    }

    public String getAddrKHVangLai() {
        return addrKHVangLai;
    }

    public void setAddrKHVangLai(String addrKHVangLai) {
        this.addrKHVangLai = addrKHVangLai;
    }

    public String getSdtKHVangLai() {
        return sdtKHVangLai;
    }

    public void setSdtKHVangLai(String sdtKHVangLai) {
        this.sdtKHVangLai = sdtKHVangLai;
    }

    public Integer getTrangThaiNhan() {
        return trangThaiNhan;
    }

    public void setTrangThaiNhan(Integer trangThaiNhan) {
        this.trangThaiNhan = trangThaiNhan;
    }

    public Integer getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(Integer trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HoaDonDTO hoaDonDTO = (HoaDonDTO) o;
        if (hoaDonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoaDonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" +
            "id=" + getId() +
            ", khachHangId=" + getKhachHangId() +
            ", loaiKH='" + getLoaiKH() + "'" +
            ", ngayLap='" + getNgayLap() + "'" +
            ", tenKHVangLai='" + getTenKHVangLai() + "'" +
            ", addrKHVangLai='" + getAddrKHVangLai() + "'" +
            ", sdtKHVangLai='" + getSdtKHVangLai() + "'" +
            ", trangThaiNhan=" + getTrangThaiNhan() +
            ", trangThaiThanhToan=" + getTrangThaiThanhToan() +
            ", note='" + getNote() + "'" +
            "}";
    }
}
