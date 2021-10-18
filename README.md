This project shows how to map a entity and setup the openjpa in a very simple way. No dependency injection is used to instantiate the EntityManager.

To run, use a mysql inside a container and use this DDL:
```
CREATE TABLE topic (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE post (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    topic_id int(11) unsigned NOT NULL,
    title varchar(16536) NOT NULL,
    text varchar(16536) NOT NULL,
  PRIMARY KEY (id),
  KEY topic_id (topic_id),
  CONSTRAINT post_ibfk_1 FOREIGN KEY (topic_id) REFERENCES topic (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

Then use `gradle build run`
