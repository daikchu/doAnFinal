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
 * A GioHang.
 */
@Document
public class GioHang implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "giohang";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @Field("token")
    private String token;

    @Field("c_tdh_id")
    private Long cTDHId;

    @Field("user_id")
    private String userId;

    @Field("date_update")
    private Instant dateUpdate;

    @Field("status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public GioHang token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getcTDHId() {
        return cTDHId;
    }

    public GioHang cTDHId(Long cTDHId) {
        this.cTDHId = cTDHId;
        return this;
    }

    public void setcTDHId(Long cTDHId) {
        this.cTDHId = cTDHId;
    }

    public String getUserId() {
        return userId;
    }

    public GioHang userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getDateUpdate() {
        return dateUpdate;
    }

    public GioHang dateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
        return this;
    }

    public void setDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getStatus() {
        return status;
    }

    public GioHang status(Integer status) {
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
        GioHang gioHang = (GioHang) o;
        if (gioHang.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gioHang.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GioHang{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", cTDHId=" + getcTDHId() +
            ", userId='" + getUserId() + "'" +
            ", dateUpdate='" + getDateUpdate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
