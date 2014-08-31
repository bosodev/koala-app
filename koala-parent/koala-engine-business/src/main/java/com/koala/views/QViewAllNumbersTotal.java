package com.koala.views;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QViewAllNumbersTotal is a Querydsl query type for ViewAllNumbersTotal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QViewAllNumbersTotal extends EntityPathBase<ViewAllNumbersTotal> {

    private static final long serialVersionUID = -1527902035L;

    public static final QViewAllNumbersTotal viewAllNumbersTotal = new QViewAllNumbersTotal("viewAllNumbersTotal");

    public final NumberPath<Integer> ball = createNumber("ball", Integer.class);

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public QViewAllNumbersTotal(String variable) {
        super(ViewAllNumbersTotal.class, forVariable(variable));
    }

    public QViewAllNumbersTotal(Path<? extends ViewAllNumbersTotal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewAllNumbersTotal(PathMetadata<?> metadata) {
        super(ViewAllNumbersTotal.class, metadata);
    }

}

