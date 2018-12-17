package com.daicq.domain;

import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.daicq.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A Users.
 */
@Document
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "users";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @NotNull
    @Field("user_name")
    private String userName;

    @NotNull
    @Size(min = 6)
    @Field("password")
    private String password;

    @Field("email")
    private String email;

    @Field("phone")
    private String phone;

    @Field("address")
    private String address;

    @Field("full_name")
    private String fullName;

    @Field("start_date")
    private LocalDate startDate;

    @Field("trang_thai")
    private Boolean trangThai;

    @Field("cmnd")
    private String cmnd;

    @Field("ngay_sinh")
    private LocalDate ngaySinh;

    @Field("gioi_tinh")
    private String gioiTinh;

    @Field("roles")
    private List<String> roles;

    @Field("date_created")
    private LocalDate dateCreated;

    @Field("date_updated")
    private LocalDate dateUpdated;

    @Field("type")
    private String type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public Users userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public Users password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Users email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Users phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public Users address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public Users fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Users startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Boolean isTrangThai() {
        return trangThai;
    }

    public Users trangThai(Boolean trangThai) {
        this.trangThai = trangThai;
        return this;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getCmnd() {
        return cmnd;
    }

    public Users cmnd(String cmnd) {
        this.cmnd = cmnd;
        return this;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public Users ngaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
        return this;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public Users gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Users roles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public Users dateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public Users dateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getType() {
        return type;
    }

    public Users type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
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
        Users users = (Users) o;
        if (users.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), users.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Users{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", trangThai='" + isTrangThai() + "'" +
            ", cmnd='" + getCmnd() + "'" +
            ", ngaySinh='" + getNgaySinh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", roles='" + getRoles() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateUpdated='" + getDateUpdated() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
