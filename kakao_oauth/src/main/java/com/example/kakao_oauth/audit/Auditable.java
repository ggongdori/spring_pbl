package com.example.kakao_oauth.audit;

public interface Auditable {
    TimeEntity getTimeEntity();
    void setTimeEntity(TimeEntity timeEntity);
}