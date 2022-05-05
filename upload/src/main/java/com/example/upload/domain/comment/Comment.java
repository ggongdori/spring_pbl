package com.example.upload.domain.comment;

import com.example.upload.domain.Timestamped;
import com.example.upload.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String contents;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

//    public static Comment saveComment(Board board) {
//        Comment comment = new Comment(board);
//        comment.getBoard().getComments().add(comment);
//        board.addComment();
//        return comment;
//    }

}
