DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'user_db') THEN
            EXECUTE 'CREATE DATABASE user_db';
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'account_db') THEN
            EXECUTE 'CREATE DATABASE account_db';
        END IF;
    END $$;
