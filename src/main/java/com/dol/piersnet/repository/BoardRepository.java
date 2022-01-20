package com.dol.piersnet.repository;

import com.dol.piersnet.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.persistence.OrderBy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findByTitle(String title);

    List<Board> findByTitleOrProductOrContentOrContent1OrContent2OrContent3OrContent4(String title, String product, String content, String content1, String content2, String content3, String content4);

    Page<Board> findByTitleContainingOrProductContainingOrContentContainingOrContent1ContainingOrContent2ContainingOrContent3ContainingOrContent4ContainingOrderByIdDesc(String title, String product, String content, String content1, String content2, String content3, String content4, Pageable pageable);

    List<Board> findByTitleOrContent(String title, String content);
}


