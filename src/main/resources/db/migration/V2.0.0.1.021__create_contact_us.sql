CREATE TABLE contact_us
(
  id            BIGSERIAL   NOT NULL
    CONSTRAINT contact_us_pkey
    PRIMARY KEY,

  name          CHARACTER VARYING(200),
  email         CHARACTER VARYING(512),
  mobile_no     CHARACTER VARYING(20),
  message       CHARACTER VARYING
);