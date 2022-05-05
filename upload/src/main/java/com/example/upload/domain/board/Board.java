package com.example.upload.domain.board;

import com.example.upload.domain.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
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
    private LocalDateTime lastModifiedAt;

    @OneToMany
    @JoinColumn(name = "board")
    private List<UploadFile> images = new ArrayList<>();



}
