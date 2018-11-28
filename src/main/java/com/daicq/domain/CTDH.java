package com.daicq.domain;

import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;

import java.io.Serializable;
import java.util.Objects;

import static com.daicq.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A CTDH.
 */
@Document
public class CTDH implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "ctdh";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @Field("sach_id")
    private Long sachId;

    @Field("so_luong")
    private Long soLuong;

    @Field("thanh_tien")
    private Float thanhTien;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSachId() {
        return sachId;
    }

    public CTDH sachId(Long sachId) {
        this.sachId = sachId;
        return this;
    }

    public void setSachId(Long sachId) {
        this.sachId = sachId;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public CTDH soLuong(Long soLuong) {
        this.soLuong = soLuong;
        return this;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public CTDH thanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
        return this;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
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
        CTDH cTDH = (CTDH) o;
        if (cTDH.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cTDH.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CTDH{" +
            "id=" + getId() +
            ", sachId=" + getSachId() +
            ", soLuong=" + getSoLuong() +
            ", thanhTien=" + getThanhTien() +
            "}";
    }
}
