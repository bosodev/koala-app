package com.koala.views;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QViewLateByNumber is a Querydsl query type for ViewLateByNumber
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QViewLateByNumber extends EntityPathBase<ViewLateByNumber> {

    private static final long serialVersionUID = 123914036L;

    public static final QViewLateByNumber viewLateByNumber = new QViewLateByNumber("viewLateByNumber");

    public final NumberPath<Integer> ball = createNumber("ball", Integer.class);

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public QViewLateByNumber(String variable) {
        super(ViewLateByNumber.class, forVariable(variable));
    }

    public QViewLateByNumber(Path<? extends ViewLateByNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewLateByNumber(PathMetadata<?> metadata) {
        super(ViewLateByNumber.class, metadata);
    }

}

