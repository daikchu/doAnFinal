package com.daicq.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NhaXuatBan entity.
 */
public class NhaXuatBanDTO implements Serializable {

    private String id;

    @NotNull
    private String ten;

    private String diaChi;

    private String sdt;

    private Float chietKhau;

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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Float getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(Float chietKhau) {
        this.chietKhau = chietKhau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NhaXuatBanDTO nhaXuatBanDTO = (NhaXuatBanDTO) o;
        if (nhaXuatBanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhaXuatBanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhaXuatBanDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", sdt='" + getSdt() + "'" +
            ", chietKhau=" + getChietKhau() +
            "}";
    }
}
