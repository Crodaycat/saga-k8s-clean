DROP SCHEMA IF EXISTS "customer" CASCADE;

CREATE SCHEMA "customer";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');

DROP TABLE IF EXISTS "customer".customers CASCADE;
CREATE TABLE "customer".customers (
    id uuid NOT NULL,
    username character varying COLLATE pg_catalog."default",
    first_name character varying COLLATE pg_catalog."default",
    last_name character varying COLLATE pg_catalog."default",
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);


DROP MATERIALIZED VIEW IF EXISTS customer.order_customer_m_view;

CREATE MATERIALIZED VIEW customer.order_customer_m_view
TABLESPACE pg_default
AS
 SELECT id,
    username,
    first_name,
    last_name
   FROM customer.customers
WITH DATA;

refresh materialized VIEW customer.order_customer_m_view;


CREATE OR REPLACE FUNCTION customer.refresh_order_customer_m_view()
RETURNS TRIGGER
AS '
    BEGIN
        refresh materialized VIEW customer.order_customer_m_view;
        return null;
    END;
' LANGUAGE plpgsql;

CREATE TRIGGER refresh_order_customer_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON customer.customers FOR each statement
EXECUTE PROCEDURE customer.refresh_order_customer_m_view();