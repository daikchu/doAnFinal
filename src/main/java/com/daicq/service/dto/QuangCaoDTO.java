package com.daicq.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the QuangCao entity.
 */
public class QuangCaoDTO implements Serializable {

    private String id;

    @NotNull
    private String tieuDe;

    private String noiDung;

    private String imageUrl;

    private Instant timeStart;

    private Instant timeEnd;

    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuangCaoDTO quangCaoDTO = (QuangCaoDTO) o;
        if (quangCaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quangCaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuangCaoDTO{" +
            "id=" + getId() +
            ", tieuDe='" + getTieuDe() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", timeStart='" + getTimeStart() + "'" +
            ", timeEnd='" + getTimeEnd() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
