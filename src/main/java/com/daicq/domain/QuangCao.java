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
 * A QuangCao.
 */
@Document
public class QuangCao implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "quangcao";

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

    @Field("image_url")
    private String imageUrl;

    @Field("time_start")
    private Instant timeStart;

    @Field("time_end")
    private Instant timeEnd;

    @Field("link")
    private String link;

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

    public QuangCao tieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
        return this;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public QuangCao noiDung(String noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public QuangCao imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public QuangCao timeStart(Instant timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public QuangCao timeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
        return this;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLink() {
        return link;
    }

    public QuangCao link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
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
        QuangCao quangCao = (QuangCao) o;
        if (quangCao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quangCao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuangCao{" +
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
