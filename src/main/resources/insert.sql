INSERT INTO MANAGER (id, birth_day, first_name, last_name, active) VALUES (1, '2000-01-01', 'Mihail', 'Sokolovsky', true);
INSERT INTO MANAGER (id, birth_day, first_name, last_name, active) VALUES (2, '1990-02-12', 'Viktor', 'Gratchev', true);
INSERT INTO MANAGER (id, birth_day, first_name, last_name, active) VALUES (3, '1995-03-13', 'Vitaly', 'Staruhin', true);
INSERT INTO MANAGER (id, birth_day, first_name, last_name, active) VALUES (4, '1998-04-16', 'Diego', 'Maradona', true);

INSERT INTO CLIENT_LEVEL_TYPE (id, title, active, code) VALUES (1, 'Applicant', true, 'APL');
INSERT INTO CLIENT_LEVEL_TYPE (id, title, active, code) VALUES (2, 'Beginner', true, 'BGN');
INSERT INTO CLIENT_LEVEL_TYPE (id, title, active, code) VALUES (3, 'Regular', true, 'REG');
INSERT INTO CLIENT_LEVEL_TYPE (id, title, active, code) VALUES (4, 'Graduate', true, 'GRD');

INSERT INTO CLIENT_STATUS_TYPE (id, title, active, code) VALUES (1, 'Active', true, 'ACT');
INSERT INTO CLIENT_STATUS_TYPE (id, title, active, code) VALUES (2, 'Frozen', true, 'FRZ');
INSERT INTO CLIENT_STATUS_TYPE (id, title, active, code) VALUES (3, 'Pending', true, 'PND');

INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (1, '1980-03-14', 'Robert', 'Levandovsky', 1, null, 3, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (2, '1986-09-08', 'Michael', 'Phelps', 1, null, 3, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (3, '1991-01-14', 'Marten', 'Fourcade', 2, 1, 1, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (4, '1996-07-30', 'Rafael', 'Nadal', 2, 1, 1, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (5, '1997-05-20', 'Sergey', 'Bubka', 2, 2, 1, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (6, '1996-02-21', 'Arvidas', 'Sabonis', 3, 2, 1, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (7, '1986-03-25', 'Oksana', 'Bayul', 3, 2, 2, true);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (8, '1980-04-03', 'Steffy', 'Graf', 4, null, 1, false);
INSERT INTO CLIENT (id, birth_day, first_name, last_name, client_level_type_id, manager_id, client_status_type_id, active) VALUES (9, '1995-09-12', 'Vitaly', 'Klichko', 3, 4, 2, true);

INSERT INTO CLIENT_STATUS_HISTORY (id, date_changed, client_id, client_status_type_id, active) VALUES (1, '2017-04-01', 7, 1, true);
INSERT INTO CLIENT_STATUS_HISTORY (id, date_changed, client_id, client_status_type_id, active) VALUES (2, '2017-04-10', 7, 2, true);
INSERT INTO CLIENT_STATUS_HISTORY (id, date_changed, client_id, client_status_type_id, active) VALUES (3, '2017-01-01', 6, 1, true);
INSERT INTO CLIENT_STATUS_HISTORY (id, date_changed, client_id, client_status_type_id, active) VALUES (4, '2017-02-01', 6, 2, true);
INSERT INTO CLIENT_STATUS_HISTORY (id, date_changed, client_id, client_status_type_id, active) VALUES (5, '2017-03-10', 6, 1, true);

INSERT INTO CONTACT_TYPE (id, title, active, code) VALUES (1, 'telephone', true, 'phone');
INSERT INTO CONTACT_TYPE (id, title, active, code) VALUES (2, 'e-mail', true, 'mail');
INSERT INTO CONTACT_TYPE (id, title, active, code) VALUES (3, 'site', true, 'site');
INSERT INTO CONTACT_TYPE (id, title, active, code) VALUES (4, 'skype', true, 'skype');
INSERT INTO CONTACT_TYPE (id, title, active, code) VALUES (5, 'city', true, 'city');

INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (1, '0503265566', 1, 1, null, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (2, 'test@gmail.com', 2, 1, null, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (3, 'www.test.ua', 3, 1, null, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (4, 'testik', 4, 1, null, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (5, 'Kyev', 5, 1, null, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (6, '0936854214', 1, null, 2, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (7, 'karamba@gmail.com', 2, null, 2, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (8, null, 3, null, 2, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (9, 'rocket56', 4, null, 2, true);
INSERT INTO CONTACT (id, contact_name, contact_type_id, client_id, manager_id, active) VALUES (10, 'Kyev', 5, null, 2, true);