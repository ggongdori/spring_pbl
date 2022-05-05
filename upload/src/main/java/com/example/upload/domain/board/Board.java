package com.example.upload.domain.board;

import com.example.upload.domain.Timestamped;
import com.example.upload.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    private String title;
    private String contents;
    private List<MultipartFile> images = new ArrayList<>();

//    @OneToMany
//    private boolean bookmark;
//    @OneToMany
//    private List<Comment> comments = new ArrayList<>();

    private Board(String title, String contents, List<MultipartFile> images) {
        this.title = title;
        this.contents = contents;
        this.images = images;
    }
//    private List<String> images = new ArrayList<>();
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;


}
