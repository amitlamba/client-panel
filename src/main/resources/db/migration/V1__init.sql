CREATE TABLE appuser
(
  id                    BIGSERIAL                                           NOT NULL
    CONSTRAINT appuser_pkey
    PRIMARY KEY,
  secret                VARCHAR(50) DEFAULT 'mySecret' :: CHARACTER VARYING NOT NULL,
  email                 VARCHAR(50)                                         NOT NULL,
  enabled               BOOLEAN                                             NOT NULL,
  firstname             VARCHAR(50)                                         NOT NULL,
  key                   VARCHAR(50),
  lastpasswordresetdate TIMESTAMP                                           NOT NULL,
  lastname              VARCHAR(50)                                         NOT NULL,
  password              VARCHAR(100)                                        NOT NULL,
  username              VARCHAR(50)                                         NOT NULL
    CONSTRAINT uk_418sr20kh207kb22viuyno1
    UNIQUE
);


CREATE TABLE authority
(
  id   BIGSERIAL   NOT NULL
    CONSTRAINT authority_pkey
    PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE user_authority
(
  user_id      BIGINT NOT NULL
    CONSTRAINT fkjk083dm06nfs1ycs8jeyjevdy
    REFERENCES appuser,
  authority_id BIGINT NOT NULL
    CONSTRAINT fkgvxjs381k6f48d5d2yi11uh89
    REFERENCES authority
);


INSERT INTO APPUSER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE)
VALUES
  (nextval('appuser_id_seq'), 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin',
   'admin@admin.com', TRUE, '01-01-2016'),
  (nextval('appuser_id_seq'), 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user',
   'enabled@user.com', TRUE, '01-01-2016'),
  (nextval('appuser_id_seq'), 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user',
   'user', 'disabled@user.com', FALSE, '01-01-2016');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);

CREATE TABLE email_template
(
  id                     BIGSERIAL               NOT NULL
    CONSTRAINT email_template_pkey
    PRIMARY KEY,
  client_id              BIGINT                  NOT NULL REFERENCES appuser,
  email_template_subject CHARACTER VARYING(1000) NOT NULL,
  email_template_body    TEXT                    NOT NULL,
  parent_id              BIGINT,
  from_user                   CHARACTER VARYING(200),
  message_type           SMALLINT                NOT NULL,
  tags                   CHARACTER VARYING(200)
);

CREATE TABLE campaign
(
  id              BIGSERIAL NOT NULL CONSTRAINT campaign_pkey PRIMARY KEY,
  client_id       BIGINT    NOT NULL,
  campaign_type   SMALLINT  NOT NULL,
  segmentation_id BIGINT    NOT NULL,
  frequency_type  SMALLINT  NOT NULL,
  schedule        TEXT      NOT NULL,
  campaign_status SMALLINT  NOT NULL
);

CREATE TABLE email_campaign
(
  id                BIGSERIAL NOT NULL CONSTRAINT email_campaign_pkey PRIMARY KEY,
  client_id         BIGINT    NOT NULL REFERENCES appuser,
  campaign_id       BIGINT    NOT NULL REFERENCES campaign,
  email_template_id BIGINT    NOT NULL REFERENCES email_template
);

CREATE TABLE campign_trigger
(
  id             BIGSERIAL NOT NULL CONSTRAINT campaign_trigger_pkey PRIMARY KEY,
  client_id      BIGINT    NOT NULL REFERENCES appuser,
  campaign_id    BIGINT    NOT NULL REFERENCES campaign,
  trigger_time   TIMESTAMP NOT NULL,
  trigger_status SMALLINT  NOT NULL
)