package com.example.oauthlogin.audit;

public interface Auditable {
    TimeEntity getTimeEntity();
    void setTimeEntity(TimeEntity timeEntity);
}