package org.warape.commons.dto;


import java.io.Serializable;

public class SendMessageResult<T> implements Serializable {

    private String uniqueId;
    private T data;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
