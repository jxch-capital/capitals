CREATE USER capitals WITH PASSWORD 'capitals';
CREATE DATABASE capitals;
GRANT ALL PRIVILEGES ON DATABASE capitals TO capitals;
ALTER DATABASE capitals OWNER to capitals;
-- 登入 capitals
GRANT ALL PRIVILEGES ON SCHEMA public TO capitals;

CREATE USER keycloak WITH PASSWORD 'keycloak';
CREATE DATABASE keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;
ALTER DATABASE keycloak OWNER to keycloak;
-- 登入 keycloak
GRANT ALL PRIVILEGES ON SCHEMA public TO keycloak;




