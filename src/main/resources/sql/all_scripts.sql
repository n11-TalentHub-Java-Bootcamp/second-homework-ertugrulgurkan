INSERT INTO public.USERS(ID, NAME, EMAIL, SURNAME, PHONE, USERNAME)
VALUES (1, 'Ertugrul', 'ertugrulgurkan@gmail.com', 'Gurkan', '5370000001', 'ertugrulg');
INSERT INTO public.USERS(ID, NAME, EMAIL, SURNAME, PHONE, USERNAME)
VALUES (2, 'Ozan', 'ozanyilmaz124578@gmail.com', 'Yilmaz', '5370059002', 'ozan1999');
INSERT INTO public.USERS(ID, NAME, EMAIL, SURNAME, PHONE, USERNAME)
VALUES (3, 'Ozlem', 'ozlemcoskun18739@gmail.com', 'Coskun', '5375624803', 'Ozlem142');
INSERT INTO public.USERS(ID, NAME, EMAIL, SURNAME, PHONE, USERNAME)
VALUES (4, 'Mustafa', 'mustafagirgin12342123@gmail.com', 'Girgin', '5370540004', 'mustafa22');


INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (1, 'Moda', 1, null);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (2, 'Elektronik', 1, null);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (3, 'Ev & Yaşam', 1, null);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (4, 'Elbise', 2, 1);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (5, 'Gömlek', 2, 1);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (6, 'Bilgisayar', 2, 2);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (7, 'Dizüstü Bilgisayar', 3, 6);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (8, 'Mobilya', 2, 3);
INSERT INTO public.CATEGORY(ID, NAME, DEPTH, ID_TOP_CATEGORY)
VALUES (9, 'Sehpa', 3, 8);


INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (1, 'Mavi Elbise', 200, now(), 4);
INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (2, 'Kırmızı Elbise', 300, now(), 4);
INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (3, 'Mavi Gömlek', 500, now(), 5);
INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (4, 'Sarı Gömlek', 450, now(), 5);
INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (5, 'HP ', 15000, now(), 7);
INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (6, 'MSI', 20000, now(), 7);
INSERT INTO public.PRODUCT(ID, NAME, PRICE, RECORD_DATE, ID_CATEGORY)
VALUES (7, 'Orta Sehpa', 600, now(), 9);

INSERT INTO public.PRODUCT_COMMENT(ID, COMMENT, COMMENT_DATE, USER_ID, PRODUCT_ID)
VALUES (1, 'Harika tavsiye edilir', now(), 1, 4);
INSERT INTO public.PRODUCT_COMMENT(ID, COMMENT, COMMENT_DATE, USER_ID, PRODUCT_ID)
VALUES (2, 'Guzel', now(), 1, 3);
INSERT INTO public.PRODUCT_COMMENT(ID, COMMENT, COMMENT_DATE, USER_ID, PRODUCT_ID)
VALUES (3, 'Begendim', now(), 2, 3);
INSERT INTO public.PRODUCT_COMMENT(ID, COMMENT, COMMENT_DATE, USER_ID, PRODUCT_ID)
VALUES (4, 'Sevdim', now(), 3, 2);
INSERT INTO public.PRODUCT_COMMENT(ID, COMMENT, COMMENT_DATE, USER_ID, PRODUCT_ID)
VALUES (5, 'Tavsiye edilir', now(), 4, 4);