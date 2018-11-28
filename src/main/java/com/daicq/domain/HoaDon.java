package com.daicq.domain;

import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import static com.daicq.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A HoaDon.
 */
@Document
public class HoaDon implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "hoadon";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @Field("khach_hang_id")
    private Long khachHangId;

    @Field("loai_kh")
    private String loaiKH;

    @Field("ngay_lap")
    private Instant ngayLap;

    @Field("ten_kh_vang_lai")
    private String tenKHVangLai;

    @Field("addr_kh_vang_lai")
    private String addrKHVangLai;

    @Field("sdt_kh_vang_lai")
    private String sdtKHVangLai;

    @Field("trang_thai_nhan")
    private Integer trangThaiNhan;

    @Field("trang_thai_thanh_toan")
    private Integer trangThaiThanhToan;

    @Field("note")
    private String note;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getKhachHangId() {
        return khachHangId;
    }

    public HoaDon khachHangId(Long khachHangId) {
        this.khachHangId = khachHangId;
        return this;
    }

    public void setKhachHangId(Long khachHangId) {
        this.khachHangId = khachHangId;
    }

    public String getLoaiKH() {
        return loaiKH;
    }

    public HoaDon loaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
        return this;
    }

    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }

    public Instant getNgayLap() {
        return ngayLap;
    }

    public HoaDon ngayLap(Instant ngayLap) {
        this.ngayLap = ngayLap;
        return this;
    }

    public void setNgayLap(Instant ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTenKHVangLai() {
        return tenKHVangLai;
    }

    public HoaDon tenKHVangLai(String tenKHVangLai) {
        this.tenKHVangLai = tenKHVangLai;
        return this;
    }

    public void setTenKHVangLai(String tenKHVangLai) {
        this.tenKHVangLai = tenKHVangLai;
    }

    public String getAddrKHVangLai() {
        return addrKHVangLai;
    }

    public HoaDon addrKHVangLai(String addrKHVangLai) {
        this.addrKHVangLai = addrKHVangLai;
        return this;
    }

    public void setAddrKHVangLai(String addrKHVangLai) {
        this.addrKHVangLai = addrKHVangLai;
    }

    public String getSdtKHVangLai() {
        return sdtKHVangLai;
    }

    public HoaDon sdtKHVangLai(String sdtKHVangLai) {
        this.sdtKHVangLai = sdtKHVangLai;
        return this;
    }

    public void setSdtKHVangLai(String sdtKHVangLai) {
        this.sdtKHVangLai = sdtKHVangLai;
    }

    public Integer getTrangThaiNhan() {
        return trangThaiNhan;
    }

    public HoaDon trangThaiNhan(Integer trangThaiNhan) {
        this.trangThaiNhan = trangThaiNhan;
        return this;
    }

    public void setTrangThaiNhan(Integer trangThaiNhan) {
        this.trangThaiNhan = trangThaiNhan;
    }

    public Integer getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public HoaDon trangThaiThanhToan(Integer trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
        return this;
    }

    public void setTrangThaiThanhToan(Integer trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getNote() {
        return note;
    }

    public HoaDon note(String note) {
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
        HoaDon hoaDon = (HoaDon) o;
        if (hoaDon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoaDon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoaDon{" +
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
