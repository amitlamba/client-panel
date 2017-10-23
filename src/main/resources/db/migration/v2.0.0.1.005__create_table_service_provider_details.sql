CREATE TABLE service_provider_details
(
  id                    BIGSERIAL   NOT NULL
    CONSTRAINT service_provider_details_pkey
    PRIMARY KEY,
  client_id             BIGINT      NOT NULL REFERENCES client,
  appuser_id            BIGINT      NOT NULL REFERENCES appuser,
  service_provider_type VARCHAR(40) NOT NULL,
  service_provider      VARCHAR(40) NOT NULL,
  url                   VARCHAR(100),
  port                  INT,
  username              VARCHAR(100),
  password              VARCHAR(100),
  date_created          TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified         TIMESTAMP WITH TIME ZONE DEFAULT now(),
  status                VARCHAR(20)
);