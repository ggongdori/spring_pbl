package com.sparta.week02.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;


@Setter
@Getter
@RequiredArgsConstructor
public class CourseRequestDto {
    private final String title;
    private final String tutor;
}
