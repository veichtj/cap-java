using db.bookshop as bookshop from '../db/data-model';
using { managed, cuid } from '@sap/cds/common';

@cds.query.limit.default: 20
@cds.query.limit.max: 100
@protocols : ['odata-v4']
service CatalogService {

    @cds.redirection.target: true
    @cds.search: {first_name}
    @odata.draft.enabled
    entity Author as
        select from bookshop.Author as author left join StatusReference as stRef on author.status = stRef.statusId {
            key author.ID,
            first_name,
            last_name,
            city,
            book,
            stRef.description
    };
   @cds.redirection.target: true
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

    event BookReservedEvent : cuid {
      bookId  : Book:ID
    }

}




