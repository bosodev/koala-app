package com.koala.views;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QViewRaffleNumberAunt is a Querydsl query type for ViewRaffleNumberAunt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QViewRaffleNumberAunt extends EntityPathBase<ViewRaffleNumberAunt> {

    private static final long serialVersionUID = -55067687L;

    public static final QViewRaffleNumberAunt viewRaffleNumberAunt = new QViewRaffleNumberAunt("viewRaffleNumberAunt");

    public final NumberPath<Integer> concurse = createNumber("concurse", Integer.class);

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public QViewRaffleNumberAunt(String variable) {
        super(ViewRaffleNumberAunt.class, forVariable(variable));
    }

    public QViewRaffleNumberAunt(Path<? extends ViewRaffleNumberAunt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewRaffleNumberAunt(PathMetadata<?> metadata) {
        super(ViewRaffleNumberAunt.class, metadata);
    }

}

