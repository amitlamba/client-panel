alter TABLE  client add COLUMN firstname varchar(255);
alter TABLE  client add COLUMN lastname varchar(255) ;
alter TABLE client add COLUMN country  varchar(255);
alter TABLE appuser alter COLUMN key TYPE varchar(2048);
alter TABLE appuser alter COLUMN secret TYPE varchar(2048);

