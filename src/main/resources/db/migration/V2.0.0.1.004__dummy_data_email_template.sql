INSERT INTO email_template(
	id, client_id, appuser_id, email_template_subject, email_template_body, parent_id, from_user, message_type, tags)
	VALUES (nextval('email_template_id_seq'), 1, 1, 'First Dummy Email Subject', 'First Dummy Email Body', null, 'amit@userndot.com', 1, null);