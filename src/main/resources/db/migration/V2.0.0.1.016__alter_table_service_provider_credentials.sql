ALTER TABLE service_provider_credentials
    ALTER COLUMN credentials TYPE text,
    drop column url,
    drop column port,
    drop column username,
    drop column password;