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
 * A NhaXuatBan.
 */
@Document
public class NhaXuatBan implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "nhaxuatban";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @NotNull
    @Field("ten")
    private String ten;

    @Field("dia_chi")
    private String diaChi;

    @Field("sdt")
    private String sdt;

    @Field("chiet_khau")
    private Float chietKhau;

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

    public NhaXuatBan ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public NhaXuatBan diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public NhaXuatBan sdt(String sdt) {
        this.sdt = sdt;
        return this;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Float getChietKhau() {
        return chietKhau;
    }

    public NhaXuatBan chietKhau(Float chietKhau) {
        this.chietKhau = chietKhau;
        return this;
    }

    public void setChietKhau(Float chietKhau) {
        this.chietKhau = chietKhau;
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
        NhaXuatBan nhaXuatBan = (NhaXuatBan) o;
        if (nhaXuatBan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhaXuatBan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhaXuatBan{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", sdt='" + getSdt() + "'" +
            ", chietKhau=" + getChietKhau() +
            "}";
    }
}
