package com.example.upload.domain.board;

import com.example.upload.domain.FileStore;
import com.example.upload.domain.Timestamped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileStore fileStore;
//    private final UploadFileRepository uploadFileRepository;

    @Transactional
    public BoardResponseDto saveBoard(BoardRequestDto requestDto) throws IOException {
//        UploadFile uploadFile = fileStore.storeFile(requestDto.getFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(requestDto.getImages());
        Board board = boardRepository.save(Board.saveBoard(requestDto.getContents(), uploadFiles));
        return new BoardResponseDto(board);
    }

    //์กฐํ์
    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }



}
