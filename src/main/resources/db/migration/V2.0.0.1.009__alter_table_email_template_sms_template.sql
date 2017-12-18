ALTER TABLE public.email_template
    ADD COLUMN name character varying(100) NOT NULL;
ALTER TABLE public.sms_template
    ADD COLUMN name character varying(100) NOT NULL;