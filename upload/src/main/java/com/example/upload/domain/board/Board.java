package com.example.upload.domain.board;

import com.example.upload.domain.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String nickname;
    private String title;
    private String contents;

    @OneToMany
    @JoinColumn(name = "board")
    private List<UploadFile> images = new ArrayList<>();



}
