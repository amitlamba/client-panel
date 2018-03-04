CREATE TABLE segment
(
  id            BIGSERIAL   NOT NULL
    CONSTRAINT segment_pkey
    PRIMARY KEY,
  client_id     BIGINT      NOT NULL REFERENCES client,
  appuser_id    BIGINT      NOT NULL REFERENCES appuser,

  name          CHARACTER VARYING(200),
  data          JSONB       NOT NULL,

  date_created  TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified TIMESTAMP WITH TIME ZONE DEFAULT now(),
  status        VARCHAR(20) NOT NULL
);