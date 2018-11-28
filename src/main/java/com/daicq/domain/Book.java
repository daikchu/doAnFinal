package com.daicq.domain;

import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import static com.daicq.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A Book.
 */
@Document
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "book";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @NotNull
    @Field("ten")
    private String ten;

    @Field("image_url")
    private String imageUrl;

    @Field("tac_gia")
    private String tacGia;

    @Field("nha_xb")
    private String nhaXB;

    @Field("nam_xb")
    private String namXB;

    @Field("mo_ta")
    private String moTa;

    @Field("gia_nhap")
    private Float giaNhap;

    @Field("gia_ban_goc")
    private Float giaBanGoc;

    @Field("gia_km")
    private Float giaKM;

    @Field("loai")
    private String loai;

    @Field("so_luong_con")
    private Long soLuongCon;

    @Field("so_luong_nhap")
    private Long soLuongNhap;

    @Field("so_lan_xem")
    private Long soLanXem;

    @Field("note")
    private String note;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public Book ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Book imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTacGia() {
        return tacGia;
    }

    public Book tacGia(String tacGia) {
        this.tacGia = tacGia;
        return this;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public Book nhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
        return this;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getNamXB() {
        return namXB;
    }

    public Book namXB(String namXB) {
        this.namXB = namXB;
        return this;
    }

    public void setNamXB(String namXB) {
        this.namXB = namXB;
    }

    public String getMoTa() {
        return moTa;
    }

    public Book moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Float getGiaNhap() {
        return giaNhap;
    }

    public Book giaNhap(Float giaNhap) {
        this.giaNhap = giaNhap;
        return this;
    }

    public void setGiaNhap(Float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Float getGiaBanGoc() {
        return giaBanGoc;
    }

    public Book giaBanGoc(Float giaBanGoc) {
        this.giaBanGoc = giaBanGoc;
        return this;
    }

    public void setGiaBanGoc(Float giaBanGoc) {
        this.giaBanGoc = giaBanGoc;
    }

    public Float getGiaKM() {
        return giaKM;
    }

    public Book giaKM(Float giaKM) {
        this.giaKM = giaKM;
        return this;
    }

    public void setGiaKM(Float giaKM) {
        this.giaKM = giaKM;
    }

    public String getLoai() {
        return loai;
    }

    public Book loai(String loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Long getSoLuongCon() {
        return soLuongCon;
    }

    public Book soLuongCon(Long soLuongCon) {
        this.soLuongCon = soLuongCon;
        return this;
    }

    public void setSoLuongCon(Long soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public Long getSoLuongNhap() {
        return soLuongNhap;
    }

    public Book soLuongNhap(Long soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
        return this;
    }

    public void setSoLuongNhap(Long soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public Long getSoLanXem() {
        return soLanXem;
    }

    public Book soLanXem(Long soLanXem) {
        this.soLanXem = soLanXem;
        return this;
    }

    public void setSoLanXem(Long soLanXem) {
        this.soLanXem = soLanXem;
    }

    public String getNote() {
        return note;
    }

    public Book note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (book.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Book{" +
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
            ", note='" + getNote() + "'" +
            "}";
    }
}
