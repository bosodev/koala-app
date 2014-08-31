package com.koala.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRaffle is a Querydsl query type for Raffle
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRaffle extends EntityPathBase<Raffle> {

    private static final long serialVersionUID = -70927798L;

    public static final QRaffle raffle = new QRaffle("raffle");

    public final NumberPath<Integer> ball1 = createNumber("ball1", Integer.class);

    public final NumberPath<Integer> ball10 = createNumber("ball10", Integer.class);

    public final NumberPath<Integer> ball11 = createNumber("ball11", Integer.class);

    public final NumberPath<Integer> ball12 = createNumber("ball12", Integer.class);

    public final NumberPath<Integer> ball13 = createNumber("ball13", Integer.class);

    public final NumberPath<Integer> ball14 = createNumber("ball14", Integer.class);

    public final NumberPath<Integer> ball15 = createNumber("ball15", Integer.class);

    public final NumberPath<Integer> ball2 = createNumber("ball2", Integer.class);

    public final NumberPath<Integer> ball3 = createNumber("ball3", Integer.class);

    public final NumberPath<Integer> ball4 = createNumber("ball4", Integer.class);

    public final NumberPath<Integer> ball5 = createNumber("ball5", Integer.class);

    public final NumberPath<Integer> ball6 = createNumber("ball6", Integer.class);

    public final NumberPath<Integer> ball7 = createNumber("ball7", Integer.class);

    public final NumberPath<Integer> ball8 = createNumber("ball8", Integer.class);

    public final NumberPath<Integer> ball9 = createNumber("ball9", Integer.class);

    public final NumberPath<Integer> concurse = createNumber("concurse", Integer.class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public QRaffle(String variable) {
        super(Raffle.class, forVariable(variable));
    }

    public QRaffle(Path<? extends Raffle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRaffle(PathMetadata<?> metadata) {
        super(Raffle.class, metadata);
    }

}

