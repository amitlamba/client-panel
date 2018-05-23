ALTER TABLE contact_us
  add COLUMN date_created TIMESTAMP WITH TIME ZONE DEFAULT now();

ALTER TABLE contact_us
  add COLUMN date_modified TIMESTAMP WITH TIME ZONE DEFAULT now();

CREATE UNIQUE INDEX contact_us_email_uindex ON public.contact_us (email);