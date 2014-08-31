package com.koala.views;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QViewNumberLessDrawn is a Querydsl query type for ViewNumberLessDrawn
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QViewNumberLessDrawn extends EntityPathBase<ViewNumberLessDrawn> {

    private static final long serialVersionUID = -1685399142L;

    public static final QViewNumberLessDrawn viewNumberLessDrawn = new QViewNumberLessDrawn("viewNumberLessDrawn");

    public final NumberPath<Integer> ball = createNumber("ball", Integer.class);

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public QViewNumberLessDrawn(String variable) {
        super(ViewNumberLessDrawn.class, forVariable(variable));
    }

    public QViewNumberLessDrawn(Path<? extends ViewNumberLessDrawn> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewNumberLessDrawn(PathMetadata<?> metadata) {
        super(ViewNumberLessDrawn.class, metadata);
    }

}

