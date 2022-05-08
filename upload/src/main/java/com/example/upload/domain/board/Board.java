package com.example.upload.domain.board;

import com.example.upload.domain.Timestamped;
import com.example.upload.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String nickname;
    private String title;
    private String contents;
    //조회수
    @Column(columnDefinition = "integer default 0")
    private int view;
    
    private boolean bookmark;
    private LocalDateTime lastModifiedAt;

    @OneToMany
    @JoinColumn(name = "board")
    private List<UploadFile> images;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private Board(String contents, List<UploadFile> images) {
        this.contents = contents;
        this.images = images;
    }

    public static Board saveBoard(String contents, List<UploadFile> images) {
        Board board = new Board(contents, images);
        return board;
    }
    private void updateBoard(String title, String contents, List<UploadFile> images) {
        this.title = title;
        this.contents = contents;
        this.images = images;
    }

    public void addBookmark(){
        this.bookmark = true;
    }
    public void cancelBookmark() {
        this.bookmark = false;
    }


}
