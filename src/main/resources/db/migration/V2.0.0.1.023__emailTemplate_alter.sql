ALTER TABLE email_template
  drop COLUMN email_template_subject;

ALTER TABLE email_template
  drop COLUMN email_template_body;

ALTER TABLE email_template
  add COLUMN email_template_subject INTEGER;

ALTER TABLE email_template
  add COLUMN email_template_body INTEGER;

CREATE UNIQUE INDEX email_template_name
  ON email_template (client_id, name);


CREATE TABLE template
(
  id            BIGSERIAL NOT NULL
    CONSTRAINT template_pkey
    PRIMARY KEY,
  client_id     BIGINT    NOT NULL REFERENCES client,
  appuser_id    BIGINT    NOT NULL REFERENCES appuser,
  template_id   BIGINT    NOT NULL REFERENCES email_template,
  template      TEXT      NOT NULL,
  name          varchar   NOT NULL,

  date_created  TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified TIMESTAMP WITH TIME ZONE DEFAULT now()
);