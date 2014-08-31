package com.koala.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRaffleDataAnalytic is a Querydsl query type for RaffleDataAnalytic
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRaffleDataAnalytic extends EntityPathBase<RaffleDataAnalytic> {

    private static final long serialVersionUID = -1128910911L;

    public static final QRaffleDataAnalytic raffleDataAnalytic = new QRaffleDataAnalytic("raffleDataAnalytic");

    public final NumberPath<Integer> average = createNumber("average", Integer.class);

    public final NumberPath<Integer> concurse = createNumber("concurse", Integer.class);

    public final NumberPath<Integer> firstRow = createNumber("firstRow", Integer.class);

    public final NumberPath<Integer> fivethRow = createNumber("fivethRow", Integer.class);

    public final NumberPath<Integer> fourthRow = createNumber("fourthRow", Integer.class);

    public final NumberPath<Integer> greaterSequence = createNumber("greaterSequence", Integer.class);

    public final NumberPath<Integer> pair = createNumber("pair", Integer.class);

    public final NumberPath<Integer> secondRow = createNumber("secondRow", Integer.class);

    public final NumberPath<Integer> sum = createNumber("sum", Integer.class);

    public final NumberPath<Integer> sumFirstRow = createNumber("sumFirstRow", Integer.class);

    public final NumberPath<Integer> sumFivethRow = createNumber("sumFivethRow", Integer.class);

    public final NumberPath<Integer> sumFourthRow = createNumber("sumFourthRow", Integer.class);

    public final NumberPath<Integer> sumSecondRow = createNumber("sumSecondRow", Integer.class);

    public final NumberPath<Integer> sumThirdRow = createNumber("sumThirdRow", Integer.class);

    public final NumberPath<Integer> thirdRow = createNumber("thirdRow", Integer.class);

    public final NumberPath<Integer> unpaired = createNumber("unpaired", Integer.class);

    public QRaffleDataAnalytic(String variable) {
        super(RaffleDataAnalytic.class, forVariable(variable));
    }

    public QRaffleDataAnalytic(Path<? extends RaffleDataAnalytic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRaffleDataAnalytic(PathMetadata<?> metadata) {
        super(RaffleDataAnalytic.class, metadata);
    }

}

