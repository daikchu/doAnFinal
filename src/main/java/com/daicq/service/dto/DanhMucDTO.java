package com.daicq.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DanhMuc entity.
 */
public class DanhMucDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhMucDTO danhMucDTO = (DanhMucDTO) o;
        if (danhMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhMucDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
