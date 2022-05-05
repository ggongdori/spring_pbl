package com.example.upload.domain.board;

import com.example.upload.domain.Timestamped;
import com.example.upload.domain.comment.Comment;
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
    private int viewCount;
    private boolean bookmark;
    private LocalDateTime lastModifiedAt;

    @OneToMany
    @JoinColumn(name = "board")
    private List<UploadFile> images = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    @JoinColumn
    private List<Comment> comments = new ArrayList<>();

    //    private Board(String contents, List<UploadFile> images) {
//        this.contents = contents;
//        this.images = images;
//    }
//
//    public static Board saveBoard(String contents, List<UploadFile> images) {
//        Board board = new Board(contents, images);
//        return board;
//    }
    private void updateBoard(String title, String contents, List<UploadFile> images) {
        this.title = title;
        this.contents = contents;
        this.images = images;
    }


}
