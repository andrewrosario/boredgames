UPDATE users
SET password = 'password'
WHERE password IS NULL;

ALTER TABLE users ALTER COLUMN password SET NOT NULL;