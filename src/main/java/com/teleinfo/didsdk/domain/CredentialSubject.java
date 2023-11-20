package com.teleinfo.didsdk.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName credential_subject
 */
@TableName(value ="credential_subject")
public class CredentialSubject implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 属性
     */
    private String attribute;

    /**
     * 属性类型
     */
    private String attributeType;

    /**
     * 属性值示例
     */
    private String valueExample;

    /**
     * 描述
     */
    private String description;

    /**
     * 使用的模板
     */
    private String schema;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 属性
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 属性
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 属性类型
     */
    public String getAttributeType() {
        return attributeType;
    }

    /**
     * 属性类型
     */
    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * 属性值示例
     */
    public String getValueExample() {
        return valueExample;
    }

    /**
     * 属性值示例
     */
    public void setValueExample(String valueExample) {
        this.valueExample = valueExample;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 使用的模板
     */
    public String getSchema() {
        return schema;
    }

    /**
     * 使用的模板
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CredentialSubject other = (CredentialSubject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAttribute() == null ? other.getAttribute() == null : this.getAttribute().equals(other.getAttribute()))
            && (this.getAttributeType() == null ? other.getAttributeType() == null : this.getAttributeType().equals(other.getAttributeType()))
            && (this.getValueExample() == null ? other.getValueExample() == null : this.getValueExample().equals(other.getValueExample()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getSchema() == null ? other.getSchema() == null : this.getSchema().equals(other.getSchema()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAttribute() == null) ? 0 : getAttribute().hashCode());
        result = prime * result + ((getAttributeType() == null) ? 0 : getAttributeType().hashCode());
        result = prime * result + ((getValueExample() == null) ? 0 : getValueExample().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getSchema() == null) ? 0 : getSchema().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attribute=").append(attribute);
        sb.append(", attributeType=").append(attributeType);
        sb.append(", valueExample=").append(valueExample);
        sb.append(", description=").append(description);
        sb.append(", schema=").append(schema);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}