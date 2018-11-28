package com.daicq.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GioHang entity.
 */
public class GioHangDTO implements Serializable {

    private String id;

    private String token;

    private Long cTDHId;

    private String userId;

    private Instant dateUpdate;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getcTDHId() {
        return cTDHId;
    }

    public void setcTDHId(Long cTDHId) {
        this.cTDHId = cTDHId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
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

        GioHangDTO gioHangDTO = (GioHangDTO) o;
        if (gioHangDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gioHangDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GioHangDTO{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", cTDHId=" + getcTDHId() +
            ", userId='" + getUserId() + "'" +
            ", dateUpdate='" + getDateUpdate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
