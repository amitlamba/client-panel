CREATE TABLE countries (
    id serial NOT NULL
        CONSTRAINT countries_pkey
        PRIMARY KEY,
    shortname   VARCHAR(3) NOT NULL,
    name    VARCHAR(150) NOT NULL,
    phonecode int NOT NULL
);

CREATE TABLE states (
  id serial NOT NULL
        CONSTRAINT states_pkey
        PRIMARY KEY,
  name varchar(100) NOT NULL,
  country_id int NOT NULL
);

CREATE TABLE cities (
  id serial NOT NULL
        CONSTRAINT cities_pkey
        PRIMARY KEY,
  name varchar(100) NOT NULL,
  state_id int NOT NULL
);

