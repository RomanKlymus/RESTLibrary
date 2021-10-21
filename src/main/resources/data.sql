insert role(name)
values ('ROLE_USER');
insert role(name)
values ('ROLE_ADMIN');

insert author(name)
values ('Author1');
insert author(name)
values ('Author2');
insert author(name)
values ('Author3');
insert author(name)
values ('Author4');
insert author(name)
values ('Author5');
insert author(name)
values ('Author6');

insert book(average_reading_hours, copies, description, is_available, title, main_author_id)
values (10, 100, 'test desc1', true, 'Title1', 1);
insert book(average_reading_hours, copies, description, is_available, title, main_author_id)
values (10, 100, 'test desc2', true, 'Title2', 1);
insert book(average_reading_hours, copies, description, is_available, title, main_author_id)
values (10, 100, 'test desc3', true, 'Title3', 3);
insert book(average_reading_hours, copies, description, is_available, title, main_author_id)
values (10, 100, 'test desc4', true, 'Title4', 4);

insert book_co_authors(book_id, co_authors_id) values (1, 4);
insert book_co_authors(book_id, co_authors_id) values (1, 5);
insert book_co_authors(book_id, co_authors_id) values (3, 1);
insert book_co_authors(book_id, co_authors_id) values (4, 2);



