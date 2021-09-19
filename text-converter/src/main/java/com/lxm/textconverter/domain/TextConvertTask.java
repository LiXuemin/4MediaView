package com.lxm.textconverter.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TextConvertTask.
 */
@Entity
@Table(name = "text_convert_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TextConvertTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "target_url")
    private String targetUrl;

    @Column(name = "convert_state")
    private String convertState;

    @Column(name = "convert_type")
    private String convertType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TextConvertTask id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public TextConvertTask sourceUrl(String sourceUrl) {
        this.setSourceUrl(sourceUrl);
        return this;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public TextConvertTask targetUrl(String targetUrl) {
        this.setTargetUrl(targetUrl);
        return this;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getConvertState() {
        return this.convertState;
    }

    public TextConvertTask convertState(String convertState) {
        this.setConvertState(convertState);
        return this;
    }

    public void setConvertState(String convertState) {
        this.convertState = convertState;
    }

    public String getConvertType() {
        return this.convertType;
    }

    public TextConvertTask convertType(String convertType) {
        this.setConvertType(convertType);
        return this;
    }

    public void setConvertType(String convertType) {
        this.convertType = convertType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TextConvertTask)) {
            return false;
        }
        return id != null && id.equals(((TextConvertTask) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TextConvertTask{" +
            "id=" + getId() +
            ", sourceUrl='" + getSourceUrl() + "'" +
            ", targetUrl='" + getTargetUrl() + "'" +
            ", convertState='" + getConvertState() + "'" +
            ", convertType='" + getConvertType() + "'" +
            "}";
    }
}
