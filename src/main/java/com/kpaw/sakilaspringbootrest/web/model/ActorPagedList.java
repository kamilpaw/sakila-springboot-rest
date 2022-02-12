package com.kpaw.sakilaspringbootrest.web.model;

import com.kpaw.sakilaspringbootrest.domain.movie.Actor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ActorPagedList extends PageImpl<Actor> {
    public ActorPagedList(List<Actor> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ActorPagedList(List<Actor> content) {
        super(content);
    }
}
