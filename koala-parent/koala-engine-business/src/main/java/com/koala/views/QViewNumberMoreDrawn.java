package com.koala.views;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QViewNumberMoreDrawn is a Querydsl query type for ViewNumberMoreDrawn
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QViewNumberMoreDrawn extends EntityPathBase<ViewNumberMoreDrawn> {

    private static final long serialVersionUID = -237963938L;

    public static final QViewNumberMoreDrawn viewNumberMoreDrawn = new QViewNumberMoreDrawn("viewNumberMoreDrawn");

    public final NumberPath<Integer> ball = createNumber("ball", Integer.class);

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public QViewNumberMoreDrawn(String variable) {
        super(ViewNumberMoreDrawn.class, forVariable(variable));
    }

    public QViewNumberMoreDrawn(Path<? extends ViewNumberMoreDrawn> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewNumberMoreDrawn(PathMetadata<?> metadata) {
        super(ViewNumberMoreDrawn.class, metadata);
    }

}

