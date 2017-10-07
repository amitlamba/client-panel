CREATE TABLE client (
  id             SERIAL       NOT NULL CONSTRAINT appuser_verification_pkey PRIMARY KEY,
  name           VARCHAR(512) NOT NULL,
  state          SMALLINT     NOT NULL    DEFAULT 1,
  email          VARCHAR(50)  NOT NULL,
  phone          VARCHAR(20),
  email_verified BOOLEAN                  DEFAULT FALSE,
  phone_verified BOOLEAN                  DEFAULT FALSE,
  date_created   TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified  TIMESTAMP WITH TIME ZONE DEFAULT now()
);


CREATE TABLE client_verification (
  id              SERIAL NOT NULL  CONSTRAINT client_verification_pkey PRIMARY KEY,
  client_id       BIGINT NOT NULL REFERENCES client,
  email_code      VARCHAR(512),
  email_code_date TIMESTAMP WITH TIME ZONE,
  phone_otp       VARCHAR(8),
  phone_otp_date  TIMESTAMP WITH TIME ZONE
);

CREATE TABLE appuser
(
  id                    BIGSERIAL                                           NOT NULL
    CONSTRAINT appuser_pkey
    PRIMARY KEY,
  client_id             BIGINT                                              NOT NULL REFERENCES client,
  secret                VARCHAR(50) DEFAULT 'mySecret' :: CHARACTER VARYING NOT NULL,
  email                 VARCHAR(50)                                         NOT NULL,
  enabled               BOOLEAN                                             NOT NULL,
  firstname             VARCHAR(50)                                         NOT NULL,
  key                   VARCHAR(50),
  lastpasswordresetdate TIMESTAMP                                           NOT NULL,
  lastname              VARCHAR(50)                                         NOT NULL,
  password              VARCHAR(100)                                        NOT NULL,
  user_type             SMALLINT                                            NOT NULL,
  phone                 VARCHAR(20),
  username              VARCHAR(50)                                         NOT NULL
    CONSTRAINT uk_418sr20kh207kb22viuyno1
    UNIQUE,
  date_created          TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified         TIMESTAMP WITH TIME ZONE DEFAULT now()
);


CREATE TABLE authority
(
  id            BIGSERIAL   NOT NULL
    CONSTRAINT authority_pkey
    PRIMARY KEY,
  name          VARCHAR(50) NOT NULL,
  date_created  TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE user_authority
(
  user_id       BIGINT NOT NULL
    CONSTRAINT fkjk083dm06nfs1ycs8jeyjevdy
    REFERENCES appuser,
  authority_id  BIGINT NOT NULL
    CONSTRAINT fkgvxjs381k6f48d5d2yi11uh89
    REFERENCES authority,
  date_created  TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified TIMESTAMP WITH TIME ZONE DEFAULT now()
);

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

CREATE TABLE email_template
(
  id                     BIGSERIAL               NOT NULL
    CONSTRAINT email_template_pkey
    PRIMARY KEY,
  client_id              BIGINT                  NOT NULL REFERENCES client,
  appuser_id             BIGINT                  NOT NULL REFERENCES appuser,
  email_template_subject CHARACTER VARYING(1000) NOT NULL,
  email_template_body    TEXT                    NOT NULL,
  parent_id              BIGINT,
  from_user              CHARACTER VARYING(200),
  message_type           SMALLINT                NOT NULL,
  tags                   CHARACTER VARYING(200),
  date_created           TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified          TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE campaign
(
  id              BIGSERIAL NOT NULL CONSTRAINT campaign_pkey PRIMARY KEY,
  client_id       BIGINT    NOT NULL REFERENCES client,
  appuser_id      BIGINT    NOT NULL REFERENCES appuser,
  campaign_type   SMALLINT  NOT NULL,
  segmentation_id BIGINT    NOT NULL,
  frequency_type  SMALLINT  NOT NULL,
  schedule        TEXT      NOT NULL,
  campaign_status SMALLINT  NOT NULL,
  date_created    TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified   TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE email_campaign
(
  id                BIGSERIAL NOT NULL CONSTRAINT email_campaign_pkey PRIMARY KEY,
  client_id         BIGINT    NOT NULL REFERENCES client,
  appuser_id        BIGINT    NOT NULL REFERENCES appuser,
  campaign_id       BIGINT    NOT NULL REFERENCES campaign,
  email_template_id BIGINT    NOT NULL REFERENCES email_template,
  date_created      TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified     TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE campign_trigger
(
  id             BIGSERIAL NOT NULL CONSTRAINT campaign_trigger_pkey PRIMARY KEY,
  client_id      BIGINT    NOT NULL REFERENCES client,
  appuser_id     BIGINT    NOT NULL REFERENCES appuser,
  campaign_id    BIGINT    NOT NULL REFERENCES campaign,
  trigger_time   TIMESTAMP NOT NULL,
  trigger_status SMALLINT  NOT NULL,
  date_created   TIMESTAMP WITH TIME ZONE DEFAULT now(),
  date_modified  TIMESTAMP WITH TIME ZONE DEFAULT now()
);