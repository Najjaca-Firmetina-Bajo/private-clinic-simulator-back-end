INSERT INTO users (id, email, password, role)
VALUES
    (1001, 'sk1@gmail.com', '$2a$10$h9fD5LSSJ/cxG6pysikeOes5ANhA05FDyYi1tiw0mlSEYk8AKQC12', 'USER'), --password 123
    (1003, 'li3@gmail.com', '$2a$10$h9fD5LSSJ/cxG6pysikeOes5ANhA05FDyYi1tiw0mlSEYk8AKQC12', 'USER');
INSERT INTO public.companies(
    averagerating, id, address, name, description)
VALUES (3.6, 1001, 'Balzakova-12-Novi Sad-Srbija-19.8370215718-45.2426030118', 'Apoteka Zdravlje', 'Duga tradicija');

INSERT INTO public.companies(
    averagerating, id, address, name, description)
VALUES (4.0, 1002, 'Bulevar Cara Lazara-23-Novi Sad-Srbija-19.8370215718-45.2426030118', 'Medicinska Laboratorija 2', 'Duga tradicija');

INSERT INTO public.companies(
    averagerating, id, address, name, description)
VALUES (4.3, 1003, 'Bulevar Oslobodjenja-7-Niš-Srbija-21.919518-43.319208', 'Bolnica Vita', 'Duga tradicija');

INSERT INTO public.companies(
    averagerating, id, address, name, description)
VALUES (4.6, 1004, 'Zdravlja-45-Kragujevac-Srbija-20.918259-44.010211', 'Zdravstveni Centar Srbija', 'Duga tradicija');

INSERT INTO public.companies(
    averagerating, id, address, name, description)
VALUES (4.9, 1005, 'Svetog Save-10-Novi Pazar-Srbija-20.5181-43.1407', 'Apoteka Srce', 'Duga tradicija');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (10, 1001, 'Digitalni rendgen aparat za pluća', 'Rendgen', 'Medicinski Aparat');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (11, 1002, 'Laboratorijski mikroskop za analizu uzoraka', 'Mikroskop', 'Laboratorijska Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (18.5, 1003, 'Defibrilator za hitne intervencije', 'Defibrilator', 'Hitna Medicinska Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (26, 1004, 'Ultrazvučni aparat za dijagnostiku trudnoće', 'Ultrazvuk', 'Ginekološka Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (9.1, 1005, 'Infuziona pumpa za preciznu administraciju lekova', 'Infuziona Pumpa', 'Intenzivna Nega Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (15, 1006, 'EKG aparat za praćenje srčane aktivnosti', 'EKG', 'Kardiovaskularna Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (22, 1007, 'Endoskopski sistem za vizualizaciju unutrašnjih organa', 'Endoskop', 'Hirurška Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (12.5, 1008, 'Inhalator za terapiju disajnih problema', 'Inhalator', 'Respiratorna Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (30, 1009, 'MRI aparat za detaljne snimke unutrašnjih struktura', 'MRI', 'Dijagnostička Oprema');

INSERT INTO public.equipment(
    price, id, description, name, type)
VALUES (14.2, 1010, 'Laboratorijski analizator za hemijske testove', 'Analizator', 'Laboratorijska Oprema');

-- Company 1 (Apoteka Zdravlje)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10027, 1001, 1001, 15);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10028, 1001, 1003, 8);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10029, 1001, 1005, 20);

-- Company 2 (Medicinska Laboratorija 2)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10030, 1002, 1002, 12);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10031, 1002, 1004, 25);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10032, 1002, 1005, 18);

-- Company 3 (Bolnica Vita)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10033, 1003, 1001, 30);

INSERT INTO public.company_equipment(
    id,company_id, equipment_id,quantity)
VALUES (10011,1003, 1002,17);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10034, 1003, 1003, 10);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10035, 1003, 1005, 15);

-- Company 4 (Zdravstveni Centar Srbija)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10036, 1004, 1002, 22);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10037, 1004, 1004, 7);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10038, 1004, 1005, 14);

-- Company 5 (Apoteka Srce)
INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10039, 1005, 1001, 18);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10040, 1005, 1003, 20);

INSERT INTO public.company_equipment(id, company_id, equipment_id, quantity)
VALUES (10041, 1005, 1005, 12);