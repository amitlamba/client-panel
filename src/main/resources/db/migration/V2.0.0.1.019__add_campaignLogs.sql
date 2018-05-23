CREATE TABLE campaign_audit_log
(
  id           BIGSERIAL NOT NULL
    CONSTRAINT campaign_audit_logs_pkey
    PRIMARY KEY,
  client_id    BIGINT    NOT NULL REFERENCES client (id),
  campaign_id  BIGINT    NOT NULL REFERENCES campaign (id),
  status       VARCHAR(20),
  action       VARCHAR(20),
  message      TEXT,
  date_created TIMESTAMP WITH TIME ZONE DEFAULT now()
);