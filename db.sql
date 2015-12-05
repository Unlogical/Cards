CREATE TABLE users(
  id bigserial,
  login varchar(32) UNIQUE,
  email varchar(32) NOT NULL UNIQUE,
  passwd varchar(64) NOT NULL,
  
  CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE card_sets(
  id bigserial,
  title varchar(128) NOT NULL,
  privacy bool NOT NULL DEFAULT(true),
  author int NOT NULL,
  creation_date date NOT NULL DEFAULT(now()),
  description varchar(512),
  
  CONSTRAINT card_sets_pk PRIMARY KEY (id),
  CONSTRAINT card_sets_author_fk FOREIGN KEY (author) REFERENCES users(id)
);

CREATE TABLE used_card_sets(
  user_id int,
  card_set_id int,
  
  CONSTRAINT used_card_sets_pk PRIMARY KEY (user_id, card_set_id),
  CONSTRAINT used_card_sets_userid_fk FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT used_card_sets_cardsetid_fk FOREIGN KEY (card_set_id) REFERENCES card_sets(id)
);

CREATE TABLE cards(
  id bigserial,
  text_a varchar(512),
  image_a varchar(2048),
  text_b varchar(512),
  image_b varchar(2048),
  reversable bool NOT NULL DEFAULT(true),
  card_set_id int NOT NULL,
  
  CONSTRAINT cards_pk PRIMARY KEY (id),
  CONSTRAINT cards_cardsetid_fk FOREIGN KEY (card_set_id) REFERENCES card_sets(id)
);

CREATE TABLE tags(
  id bigserial,
  title varchar(32) NOT NULL UNIQUE,
  
  CONSTRAINT tags_pk PRIMARY KEY (id)
);

CREATE TABLE card_set_tags(
  tag_id int,
  card_set_id int,
  
  CONSTRAINT card_set_tags_pk PRIMARY KEY (tag_id, card_set_id),
  CONSTRAINT card_set_tags_tag_fk FOREIGN KEY (tag_id) REFERENCES tags(id),
  CONSTRAINT card_set_tags_cardsetid_fk FOREIGN KEY (card_set_id) REFERENCES card_sets(id)
);

CREATE TABLE statistics(
  user_id int,
  card_id int,
  
  CONSTRAINT statistics_pk PRIMARY KEY (user_id, card_id),
  CONSTRAINT statistics_userid_fk FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT statistics_cardid_fk FOREIGN KEY (card_id) REFERENCES cards(id)
);

CREATE TABLE sessions(
  user_id int,
  session_id varchar(64) NOT NULL,
  started_at date NOT NULL DEFAULT(now()),

  CONSTRAINT sessions_pk PRIMARY KEY (session_id),
  CONSTRAINT sessions_userid_fk FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users(login, email, passwd) VALUES('fluttershy', 'flutter@shy.com', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'); --passwd = 1
INSERT INTO card_sets(title, author, description) VALUES('Птицы', 1, 'Выучи названия всех птиц!');
INSERT INTO used_card_sets(user_id, card_set_id) VALUES(1,1);
INSERT INTO cards(image_a, text_b, card_set_id, reversable) VALUES('http://nohost.com/1.jpg', 'Дятел', 1, false);
INSERT INTO cards(text_a, text_b, card_set_id) VALUES('Starling', 'Скворец', 1);
INSERT INTO tags(title) VALUES('птицы');
INSERT INTO card_set_tags values (1,1);
INSERT INTO statistics values (1,1);

select * from cards where card_set_id in (select card_set_id from card_set_tags where tag_id = (select id from tags where title='птицы'));

