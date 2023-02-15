using db.bookshop as bookshop from '../db/data-model';

@cds.query.limit.default: 20
@cds.query.limit.max: 100
@path: 'v1/path/api'
service CatalogService {

    @cds.redirection.target: true
    @cds.search: {first_name}
    entity Author as
        select from bookshop.Author as author left join StatusReference as stRef on author.status = stRef.statusId {
            key author.ID,
            first_name,
            last_name,
            city,
            book,
            stRef.description
    };
    entity Book as projection on bookshop.Book {
        *,
        author.first_name as authorFirstName
     };

    entity StatusReference as select from bookshop.Status {
        key statusId,
        description
   };


    action reserveBook () returns reserveBookMessage;
    type reserveBookMessage { ack: String enum { yes; nope; }};
}




