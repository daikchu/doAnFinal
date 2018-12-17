package com.daicq.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Book entity.
 */
public class BookDTO implements Serializable {

    private String id;

    @NotNull
    private String ten;

    private String imageUrl;

    private String tacGia;

    private String nhaXB;

    private String namXB;

    private String moTa;

    private Float giaNhap;

    private Float giaBanGoc;

    private Float giaKM;

    private String loai;

    private Long soLuongCon;

    private Long soLuongNhap;

    private Long soLanXem;

    private String note;

    private String danhMucId;

    public String getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(String danhMucId) {
        this.danhMucId = danhMucId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getNamXB() {
        return namXB;
    }

    public void setNamXB(String namXB) {
        this.namXB = namXB;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Float getGiaBanGoc() {
        return giaBanGoc;
    }

    public void setGiaBanGoc(Float giaBanGoc) {
        this.giaBanGoc = giaBanGoc;
    }

    public Float getGiaKM() {
        return giaKM;
    }

    public void setGiaKM(Float giaKM) {
        this.giaKM = giaKM;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Long getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(Long soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public Long getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(Long soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public Long getSoLanXem() {
        return soLanXem;
    }

    public void setSoLanXem(Long soLanXem) {
        this.soLanXem = soLanXem;
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

        BookDTO bookDTO = (BookDTO) o;
        if (bookDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", tacGia='" + getTacGia() + "'" +
            ", nhaXB='" + getNhaXB() + "'" +
            ", namXB='" + getNamXB() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", giaNhap=" + getGiaNhap() +
            ", giaBanGoc=" + getGiaBanGoc() +
            ", giaKM=" + getGiaKM() +
            ", loai='" + getLoai() + "'" +
            ", soLuongCon=" + getSoLuongCon() +
            ", soLuongNhap=" + getSoLuongNhap() +
            ", soLanXem=" + getSoLanXem() +
            ", danhMucId=" + getDanhMucId() +
            ", note='" + getNote() + "'" +
            "}";
    }
}
