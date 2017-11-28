INSERT INTO CLIENT(ID,name, state, email)
    VALUES(nextval('client_id_seq'), 'und', 1, 'admin@undot.com');

INSERT INTO APPUSER (ID, client_id , user_type,  USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE)
VALUES
  (nextval('appuser_id_seq'), 1, 1, 'undadmin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'userndot', 'admin',
   'admin@admin.com', TRUE, now());


INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);