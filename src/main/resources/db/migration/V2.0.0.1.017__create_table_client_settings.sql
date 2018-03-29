CREATE TABLE client_settings (
  id              SERIAL NOT NULL CONSTRAINT client_settings_pkey PRIMARY KEY,
  client_id       BIGINT NOT NULL REFERENCES client UNIQUE,
  sender_email_addresses    TEXT,
  authorized_urls           TEXT,
  timezone                  VARCHAR(50),
  date_created timestamp with time zone NOT NULL DEFAULT now(),
  date_modified timestamp with time zone NOT NULL DEFAULT now()
);