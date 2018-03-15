create table sms_campaign
(
	id bigserial not null
		constraint sms_campaign_pkey
			primary key,
	client_id bigint not null
		constraint sms_campaign_client_id_fkey
			references client,
	appuser_id bigint not null
		constraint sms_campaign_appuser_id_fkey
			references appuser,
	campaign_id bigint not null
		constraint sms_campaign_campaign_id_fkey
			references campaign,
	sms_template_id bigint not null
		constraint sms_campaign_sms_template_id_fkey
			references sms_template,
	date_created timestamp with time zone default now(),
	date_modified timestamp with time zone default now()
)
;



