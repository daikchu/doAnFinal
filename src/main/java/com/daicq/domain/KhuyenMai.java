package com.daicq.domain;

import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import static com.daicq.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A KhuyenMai.
 */
@Document
public class KhuyenMai implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "khuyenmai";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @NotNull
    @Field("tieu_de")
    private String tieuDe;

    @Field("noi_dung")
    private String noiDung;

    @Field("loai_ap_dung")
    private String loaiApDung;

    @Field("time_start")
    private Instant timeStart;

    @Field("time_end")
    private Instant timeEnd;

    @Field("muc_km")
    private Float mucKM;

    @Field("status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public KhuyenMai tieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
        return this;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public KhuyenMai noiDung(String noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLoaiApDung() {
        return loaiApDung;
    }

    public KhuyenMai loaiApDung(String loaiApDung) {
        this.loaiApDung = loaiApDung;
        return this;
    }

    public void setLoaiApDung(String loaiApDung) {
        this.loaiApDung = loaiApDung;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public KhuyenMai timeStart(Instant timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public KhuyenMai timeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
        return this;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Float getMucKM() {
        return mucKM;
    }

    public KhuyenMai mucKM(Float mucKM) {
        this.mucKM = mucKM;
        return this;
    }

    public void setMucKM(Float mucKM) {
        this.mucKM = mucKM;
    }

    public Integer getStatus() {
        return status;
    }

    public KhuyenMai status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        KhuyenMai khuyenMai = (KhuyenMai) o;
        if (khuyenMai.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khuyenMai.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
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
