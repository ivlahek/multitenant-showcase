create TABLE public.tenant (
    id int NOT NULL,
    external_id character varying(255) not null,
    name character varying(255) NULL,
	CONSTRAINT city_pkey PRIMARY KEY (id)
);

create TABLE public.user_account (
    id int NOT NULL,
    first_name character varying(255) NULL,
    last_name character varying(255) NULL,
    tenant_id int not null,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);

create TABLE public.mobile_application (
    id int NOT NULL,
    name character varying(255) not null,
    tenant_id int not null,
    user_account_id int not null
);

create sequence public.user_sequence
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create sequence public.tenant_sequence
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create sequence public.mobile_application_sequence
    start with 1
    increment by 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;