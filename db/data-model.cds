namespace db.bookshop;
using {
    Currency,
    sap,
    managed,
    cuid
} from '@sap/cds/common';

entity Author: cuid, managed {
   first_name : String(50);
   last_name : String(50);
   city : String;
   status: String;
   book : Association to many Book on book.author = $self;
}

entity Book {
  key ID : Integer;
  title  : String;
  stock  : Integer;
  author : Association to Author;
  price : Decimal(10,3);
  curr : Currency;
  isbn : isbn;
}

entity Status {
  key statusId : String(50);
  description : String(200);
}

type isbn {
   number : String(30);
   format : String(3);
}
