CREATE TABLE sms_template
(
  id                BIGSERIAL   NOT NULL
    CONSTRAINT sms_template_pkey
    PRIMARY KEY,
  client_id         BIGINT      NOT NULL REFERENCES client,
  appuser_id        BIGINT      NOT NULL REFERENCES appuser,
  sms_template_body TEXT        NOT NULL,
  parent_id         BIGINT,
  from_user         CHARACTER VARYING(200),
  message_type      VARCHAR(40) NOT NULL,
  tags              CHARACTER VARYING(200),
  date_created      TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified     TIMESTAMP WITH TIME ZONE DEFAULT now(),
  status            VARCHAR(20) NOT NULL
);