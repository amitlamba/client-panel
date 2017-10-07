INSERT INTO CLIENT(ID,name, state, email)
VALUES(nextval('client_id_seq'), 'test', 1, 'test@undot.com');

INSERT INTO APPUSER (ID, client_id,user_type, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE)
VALUES
  (nextval('appuser_id_seq'), 2,1,'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin',
   'admin@admin.com', TRUE, '01-01-2016'),
  (nextval('appuser_id_seq'),2,2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user',
   'enabled@user.com', TRUE, '01-01-2016'),
  (nextval('appuser_id_seq'),2, 2,'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user',
   'user', 'disabled@user.com', FALSE, '01-01-2016');


INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 1);