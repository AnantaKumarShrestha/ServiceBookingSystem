--
-- PostgreSQL database dump
--

-- Dumped from database version 12.17 (Debian 12.17-1.pgdg120+1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: opt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opt (
    id uuid NOT NULL,
    created_at date,
    email character varying(255),
    expire_at timestamp(6) without time zone,
    opt character varying(255)
);


ALTER TABLE public.opt OWNER TO postgres;

--
-- Name: post_model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post_model (
    id uuid NOT NULL,
    description character varying(255),
    image_bytes oid,
    price double precision,
    service_name character varying(255),
    user_id uuid
);


ALTER TABLE public.post_model OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    address character varying(255),
    email character varying(255),
    f_name character varying(255),
    joining_date date,
    l_name character varying(255),
    password character varying(255),
    phone character varying(255),
    role character varying(255),
    verified boolean NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: opt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.opt (id, created_at, email, expire_at, opt) FROM stdin;
\.


--
-- Data for Name: post_model; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.post_model (id, description, image_bytes, price, service_name, user_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, address, email, f_name, joining_date, l_name, password, phone, role, verified) FROM stdin;
c590f532-7e50-46b5-bdb4-82ac56ccb277	\N	anantashrestha45@gmail.com	ANANasasasaskoko	2024-04-09	\N	anantashrest34@gmail.com	\N	USER	f
823bf3a4-79f0-4376-8e97-e68df4deb3d0	\N	anantashrestha45@gmail.com	ANANasasasaskoko	2024-04-09	\N	anantashrest34@gmail.com	\N	USER	f
85bc9775-09b1-4097-8e3c-79a0c2f2c3bc	\N	anantashrestha45@gmail.com	ANANasasasaskoko	2024-04-09	\N	anantashrest34@gmail.com	\N	USER	f
\.


--
-- Name: opt opt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opt
    ADD CONSTRAINT opt_pkey PRIMARY KEY (id);


--
-- Name: post_model post_model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post_model
    ADD CONSTRAINT post_model_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: post_model fkgw98qgaibhagrsuv7sjlsa9d2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post_model
    ADD CONSTRAINT fkgw98qgaibhagrsuv7sjlsa9d2 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

