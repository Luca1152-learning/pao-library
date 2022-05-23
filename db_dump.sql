--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3 (Ubuntu 14.3-1.pgdg20.04+1)
-- Dumped by pg_dump version 14.2

-- Started on 2022-05-23 23:45:12 EEST

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

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: gpjltautyhuyqg
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO gpjltautyhuyqg;

--
-- TOC entry 4379 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: gpjltautyhuyqg
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 70641)
-- Name: authors; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.authors
(
    author_id integer                NOT NULL,
    name      character varying(128) NOT NULL
);


ALTER TABLE public.authors
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 210 (class 1259 OID 70647)
-- Name: authors_author_id_seq; Type: SEQUENCE; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE public.authors
    ALTER COLUMN author_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.authors_author_id_seq
START
WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- TOC entry 211 (class 1259 OID 70649)
-- Name: books; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.books
(
    book_id                integer                NOT NULL,
    title                  character varying(128) NOT NULL,
    description            text                   NOT NULL,
    publisher_id           integer                NOT NULL,
    first_publication_year integer                NOT NULL,
    pages_count            integer                NOT NULL,
    available_copies       integer                NOT NULL
);


ALTER TABLE public.books
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 212 (class 1259 OID 70654)
-- Name: books_authors; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.books_authors
(
    book_id   integer NOT NULL,
    author_id integer NOT NULL
);


ALTER TABLE public.books_authors
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 213 (class 1259 OID 70658)
-- Name: books_book_id_seq; Type: SEQUENCE; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE public.books
    ALTER COLUMN book_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.books_book_id_seq
START
WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- TOC entry 214 (class 1259 OID 70659)
-- Name: books_categories; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.books_categories
(
    book_id     integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.books_categories
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 215 (class 1259 OID 70665)
-- Name: borrows; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.borrows
(
    borrow_id   integer NOT NULL,
    user_id     integer NOT NULL,
    book_id     integer NOT NULL,
    borrow_date date,
    return_date date
);


ALTER TABLE public.borrows
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 216 (class 1259 OID 70669)
-- Name: borrows_borrow_id_seq; Type: SEQUENCE; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE public.borrows
    ALTER COLUMN borrow_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.borrows_borrow_id_seq
START
WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- TOC entry 217 (class 1259 OID 70670)
-- Name: categories; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.categories
(
    category_id integer                NOT NULL,
    name        character varying(128) NOT NULL
);


ALTER TABLE public.categories
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 218 (class 1259 OID 70679)
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE public.categories
    ALTER COLUMN category_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.categories_category_id_seq
START
WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- TOC entry 219 (class 1259 OID 70681)
-- Name: publishers; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.publishers
(
    publisher_id integer                NOT NULL,
    name         character varying(128) NOT NULL
);


ALTER TABLE public.publishers
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 220 (class 1259 OID 70684)
-- Name: publishers_publisher_id_seq; Type: SEQUENCE; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE public.publishers
    ALTER COLUMN publisher_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.publishers_publisher_id_seq
START
WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- TOC entry 221 (class 1259 OID 70688)
-- Name: roles; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.roles
(
    role character varying(64) NOT NULL
);


ALTER TABLE public.roles
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 222 (class 1259 OID 70692)
-- Name: users; Type: TABLE; Schema: public; Owner: gpjltautyhuyqg
--

CREATE TABLE public.users
(
    user_id    integer               NOT NULL,
    username   character varying(64) NOT NULL,
    password   character varying(64) NOT NULL,
    first_name character varying     NOT NULL,
    last_name  character varying     NOT NULL,
    role       character varying(64) NOT NULL
);


ALTER TABLE public.users
    OWNER TO gpjltautyhuyqg;

--
-- TOC entry 223 (class 1259 OID 70697)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE public.users
    ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
START
WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- TOC entry 4359 (class 0 OID 70641)
-- Dependencies: 209
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.authors (author_id, name) FROM stdin;
1	Jane Austen
2	George Orwell
3	Test Author
8	Harper Lee
9	Ray Bradbury
\.


--
-- TOC entry 4361 (class 0 OID 70649)
-- Dependencies: 211
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.books (book_id, title, description, publisher_id, first_publication_year, pages_count,
                   available_copies) FROM stdin;
3	Pride and Prejudice	Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language. Jane Austen called this brilliant work "her own darling child" and its vivacious heroine, Elizabeth Bennet, "as delightful a creature as ever appeared in print." The romantic clash between the opinionated Elizabeth and her proud beau, Mr. Darcy, is a splendid performance of civilized sparring. And Jane Austen's radiant wit sparkles as her characters dance a delicate quadrille of flirtation and intrigue, making this book the most superb comedy of manners of Regency England.	1	1813	279	1
13	To Kill a Mockingbird	The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it. "To Kill A Mockingbird" became both an instant bestseller and a critical success when it was first published in 1960. It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic.	8	1960	336	5
14	Animal Farm	A farm is taken over by its overworked, mistreated animals. With flaming idealism and stirring slogans, they set out to create a paradise of progress, justice, and equality. Thus the stage is set for one of the most telling satiric fables ever penned –a razor-edged fairy tale for grown-ups that records the evolution from revolution against tyranny to a totalitarianism just as terrible.	9	1945	141	10
9	1984	Among the seminal texts of the 20th century, Nineteen Eighty-Four is a rare work that grows more haunting as its futuristic purgatory becomes more real. Published in 1949, the book offers political satirist George Orwell's nightmarish vision of a totalitarian, bureaucratic world and one poor stiff's attempt to find individuality. The brilliance of the novel is Orwell's prescience of modern life—the ubiquity of television, the distortion of the language—and his ability to construct such a thorough version of hell. Required reading for students since it was published, it ranks among the most terrifying novels ever written.	3	1949	298	0
15	Fahrenheit 451	Guy Montag is a fireman. His job is to destroy the most illegal of commodities, the printed book, along with the houses in which they are hidden. Montag never questions the destruction and ruin his actions produce, returning each day to his bland life and wife, Mildred, who spends all day with her television “family.” But when he meets an eccentric young neighbor, Clarisse, who introduces him to a past where people didn’t live in fear and to a present where one sees the world through the ideas in books instead of the mindless chatter of television, Montag begins to question everything he has ever known.	10	1953	194	7
7	Sense and Sensibility	'The more I know of the world, the more am I convinced that I shall never see a man whom I can really love. I require so much!'\n\nMarianne Dashwood wears her heart on her sleeve, and when she falls in love with the dashing but unsuitable John Willoughby she ignores her sister Elinor's warning that her impulsive behaviour leaves her open to gossip and innuendo. Meanwhile Elinor, always sensitive to social convention, is struggling to conceal her own romantic disappointment, even from those closest to her. Through their parallel experience of love—and its threatened loss—the sisters learn that sense must mix with sensibility if they are to find personal happiness in a society where status and money govern the rules of love.\nThis edition includes explanatory notes, textual variants between the first and second editions, and Tony Tanner's introduction to the original Penguin Classic edition.	2	1811	409	1
\.


--
-- TOC entry 4362 (class 0 OID 70654)
-- Dependencies: 212
-- Data for Name: books_authors; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.books_authors (book_id, author_id) FROM stdin;
3	1
7	1
9	2
3	3
13	8
14	2
15	9
\.


--
-- TOC entry 4364 (class 0 OID 70659)
-- Dependencies: 214
-- Data for Name: books_categories; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.books_categories (book_id, category_id) FROM stdin;
3	1
3	2
3	3
7	1
7	2
7	3
9	1
9	2
9	4
9	5
13	1
13	2
13	10
13	11
14	1
14	2
14	5
14	10
14	11
15	1
15	2
15	4
15	5
15	10
15	11
15	12
\.


--
-- TOC entry 4365 (class 0 OID 70665)
-- Dependencies: 215
-- Data for Name: borrows; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.borrows (borrow_id, user_id, book_id, borrow_date, return_date) FROM stdin;
8	10	7	\N	\N
9	10	3	2022-05-23	\N
\.


--
-- TOC entry 4367 (class 0 OID 70670)
-- Dependencies: 217
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.categories (category_id, name) FROM stdin;
1	Classics
2	Fiction
3	Romance
4	Science Fiction
5	Science Fiction > Dystopia
10	Academic > School
11	Novels
12	Adult
\.


--
-- TOC entry 4369 (class 0 OID 70681)
-- Dependencies: 219
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.publishers (publisher_id, name) FROM stdin;
1	Modern Library Classics, USA / CAN
2	Penguin Books
3	Houghton Mifflin Harcourt
8	Harper Perennial Modern Classics
9	Signet Classics
10	Simon & Schuster
\.


--
-- TOC entry 4371 (class 0 OID 70688)
-- Dependencies: 221
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.roles (role) FROM stdin;
librarian
user
\.


--
-- TOC entry 4372 (class 0 OID 70692)
-- Dependencies: 222
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: gpjltautyhuyqg
--

COPY public.users (user_id, username, password, first_name, last_name, role) FROM stdin;
7	admin	8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918	Library	Admin	librarian
10	luca	d70f47790f689414789eeff231703429c7f88a10210775906460edbf38589d90	Luca	Trusca	user
\.


--
-- TOC entry 4382 (class 0 OID 0)
-- Dependencies: 210
-- Name: authors_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gpjltautyhuyqg
--

SELECT pg_catalog.setval('public.authors_author_id_seq', 9, true);


--
-- TOC entry 4383 (class 0 OID 0)
-- Dependencies: 213
-- Name: books_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gpjltautyhuyqg
--

SELECT pg_catalog.setval('public.books_book_id_seq', 15, true);


--
-- TOC entry 4384 (class 0 OID 0)
-- Dependencies: 216
-- Name: borrows_borrow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gpjltautyhuyqg
--

SELECT pg_catalog.setval('public.borrows_borrow_id_seq', 9, true);


--
-- TOC entry 4385 (class 0 OID 0)
-- Dependencies: 218
-- Name: categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gpjltautyhuyqg
--

SELECT pg_catalog.setval('public.categories_category_id_seq', 13, true);


--
-- TOC entry 4386 (class 0 OID 0)
-- Dependencies: 220
-- Name: publishers_publisher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gpjltautyhuyqg
--

SELECT pg_catalog.setval('public.publishers_publisher_id_seq', 10, true);


--
-- TOC entry 4387 (class 0 OID 0)
-- Dependencies: 223
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: gpjltautyhuyqg
--

SELECT pg_catalog.setval('public.users_user_id_seq', 10, true);


--
-- TOC entry 4189 (class 2606 OID 70719)
-- Name: authors authors_name_key; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_name_key UNIQUE (name);


--
-- TOC entry 4191 (class 2606 OID 70721)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (author_id);


--
-- TOC entry 4193 (class 2606 OID 70723)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);


--
-- TOC entry 4195 (class 2606 OID 70725)
-- Name: books books_title_key; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_title_key UNIQUE (title);


--
-- TOC entry 4197 (class 2606 OID 70727)
-- Name: borrows borrows_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.borrows
    ADD CONSTRAINT borrows_pkey PRIMARY KEY (borrow_id);


--
-- TOC entry 4199 (class 2606 OID 70729)
-- Name: categories categories_name_key; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_name_key UNIQUE (name);


--
-- TOC entry 4201 (class 2606 OID 70731)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);


--
-- TOC entry 4203 (class 2606 OID 70733)
-- Name: publishers publishers_name_key; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_name_key UNIQUE (name);


--
-- TOC entry 4205 (class 2606 OID 70735)
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (publisher_id);


--
-- TOC entry 4207 (class 2606 OID 70737)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role);


--
-- TOC entry 4209 (class 2606 OID 70739)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4211 (class 2606 OID 70741)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 4213 (class 2606 OID 90003)
-- Name: books_authors books_authors_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT books_authors_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.authors (author_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4214 (class 2606 OID 90008)
-- Name: books_authors books_authors_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT books_authors_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books (book_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4215 (class 2606 OID 90035)
-- Name: books_categories books_categories_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books_categories
    ADD CONSTRAINT books_categories_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books (book_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4216 (class 2606 OID 90040)
-- Name: books_categories books_categories_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books_categories
    ADD CONSTRAINT books_categories_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories (category_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4212 (class 2606 OID 90025)
-- Name: books books_publisher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_publisher_id_fkey FOREIGN KEY (publisher_id) REFERENCES public.publishers (publisher_id) ON UPDATE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4217 (class 2606 OID 90046)
-- Name: borrows borrows_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.borrows
    ADD CONSTRAINT borrows_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books (book_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4218 (class 2606 OID 90051)
-- Name: borrows borrows_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.borrows
    ADD CONSTRAINT borrows_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users (user_id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4219 (class 2606 OID 90056)
-- Name: users users_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gpjltautyhuyqg
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_fkey FOREIGN KEY (role) REFERENCES public.roles (role) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE NOT VALID;


--
-- TOC entry 4380 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: gpjltautyhuyqg
--

REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO gpjltautyhuyqg;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 4381 (class 0 OID 0)
-- Dependencies: 863
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO gpjltautyhuyqg;


-- Completed on 2022-05-23 23:45:31 EEST

--
-- PostgreSQL database dump complete
--

