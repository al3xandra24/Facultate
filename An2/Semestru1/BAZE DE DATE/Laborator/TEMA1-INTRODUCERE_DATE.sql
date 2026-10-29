-- am adaugat tot tabelul
INSERT INTO Utilizatori(nume, email)
VALUES
('Vranceanu Alexandra', 'alexandra.v@gmail.com'),
('Radu Andrei', 'andrei.r@email.com'),
('Stan Elena', 'elena.s@email.com'),
('Ionescu Raluca', 'raluca.i@gmail.com'),
('Marian Florin', 'florin.m@gmail.com'),
('Barbu Diana', 'diana.b@gmail.com'),
('Mihai Ioana', 'ioana.m.@gmail.com'),
('Gruia Anton', 'anton.g@gmail.com'),
('Chirila Andrei', 'andrei.c@gmail.com'),
('Dumitrescu Alexandru', 'alexandru.d@gmail.com');


--am adaugat tot tabelul
INSERT INTO Proiecte(denumire, descriere, data_creare)
VALUES
('Dezvoltare aplicatie mobila', 'Lansare versiune pentru Android si IOS', '2026-02-24'),
('Campanie marketing', 'Promovare produse de iarna', '2026-10-11' ),
('Campanie promotii black-friday', 'Pregatirea materialelor promotionale, actualizarea preturilor', '2026-10-01'),
('Recrutari seniori IT', 'Interviuri si teste tehnice', '2026-08-15'),
('Actualizare date bancare angajati', 'Colectare extrase de cont si coduri IBAN angajati', '2025-08-27'),
('Deschidere sediu nou', 'Cautare spatiu si amenajare birouri', '2026-05-01'),
('Sondaj Satisfactie Clienti', 'Creare chestionar si trimitere prin email clientilor', '2026-10-15');


-- am adaugat tot tabelul
INSERT INTO Sarcini(denumire, descriere, data_limita, cod_utilizator, cod_proiect)
VALUES
('Dezvoltare interfata utilizator', 'Scriere si integrare cod', '2026-12-10', 1, 1),
('Lansare aplicatie', 'Urcarea aplicatiei pe AppStore si Google Play', '2027-02-10', 4, 1),
('Publicitate pe retele sociale', 'Creare postari pe Instagram si Facebook', '2026-11-02', 8, 2),
('Verificare de securitate a aplicatiei', 'Testare pe dispozitive multiple', '2027-01-10', 6, 1),
('Verificare date bancare primite', 'Verificare si validare extrase de cont primite de la angajati', '2026-12-06', 5, 5),
('Actualizare date validate', 'Actualizare coduri IBAN in platforma interna', '2026-12-30', 5, 5),
('Stabilire buget reclame', 'Alocare de sume pentru reclamele de pe Instagram/Facebook', '2026-10-25', 7, 2),
('Publicitate pe retele sociale', 'Creare postari pe Instagram si Facebook', '2026-11-02', 8, 3),
('Lansare anunturi recrutare angajati', 'Lansare anunturi pe LinkedIn si Ejobs', '2026-10-30', 8, 4),
('Vizionare spatii', 'Selectare locatii, vizionare si semnarea unui contract', '2027-03-01', 3, 6);


--am adaugat tot tabelul
INSERT INTO SarciniRecurente(denumire, frecventa_valoare, frecventa_unitate_timp, este_activa, cod_utilizator)
VALUES
('Programare si organizare sedinta', 1, 'Saptamana', 1, 5),
('Plata salariilor si a bonusurilor recurente', 1, 'Luna', 1, 8),
('Trimitere email promotional clientilor', 1, 'Saptamana', 0, 9),
('Comandare materiale consumabile birou', 2, 'Luna', 1, 6),
('Plata facturilor pentru utilitati', 1, 'Luna', 1, 6),
('Back-up baza de date si fisiere', 2, 'Luna', 1, 7),
('Mentenanta dispozitive electronice', 3, 'An', 1, 4),
('Creare calendar concedii anuale angajati', 1, 'An', 1, 8);

--am adaugat tot tabelul
INSERT INTO Categorii(denumire)
VALUES
('IT'),
('Marketing & Vanzari'),
('Financiar & Contabilitate'),
('Administratie'),
('Resurse Umane');


--am adaugat tot tabelul
INSERT INTO SarciniCategorii(cod_task, cod_categorie)
VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 1),
(5, 3),
(5, 4),
(6, 4),
(7, 2),
(7, 3),
(8, 2),
(9, 5),
(9, 4),
(10, 4),
(10, 3);

--am adaugat tot tabelul
INSERT INTO Membri_Proiect(cod_proiect, cod_utilizator, rol_in_proiect)
VALUES
(1, 1, 'Software Developer'),
(1, 6, 'Tester Software'),
(1, 4, 'FrontEnd Developer'),
(2, 8, 'Manager Social Media'),
(5, 5, 'Operator Validare si Introducere Date'),
(2, 7, 'Analist financiar'),
(3, 8, 'Manager Social Media'),
(4, 8, 'Manager Social Media'),
(6, 3, 'Administrator');


-- am adaugat tot tabelul
INSERT INTO Atasamente(nume_fisier, dimensiune_in_MB, cale_fisier, data_incarcare, cod_task)
VALUES
('Contract_Inchiriere_Sediu_Semnat.pdf', 2.4, '/fisiere/legal/contracte', '2026-10-06', 1),
('Model_anunt_angajati.pdf', 1.2, '/fisiere/hr/anunturi', '2026-10-02', 9), 
('Cod_Sursa_Aplicatie.zip', 30, '/fisiere/it/exemplare_aplicatii', '2026-09-15', 1),
('Plan_buget_2027.xlsx', 1.8, '/fisiere/administratie', '2026-10-14', 7),
('Adeverinta_actualizare_date.pdf', 0.5, '/fisiere/contabilitate', '2026-10-02', 6),
('Cerinte_viitori_angajati.xlsx', 1.9, 'fisiere/administratie', '2026-08-30', 9),
('Cod_Sursa_Aplicatie_v2.zip', 35, '/fisiere/it/exemplare_aplicatiii', '2026-10-01', 2);

-- am adaugat tot tabelul
INSERT INTO Comentarii(text_comentariu, data_adaugare, cod_proiect, cod_utilizator)
VALUES
('Unul din documentele primite contine un IBAN invalid', '2026-10-03', 5, 5),
('Am postat anunturi pe toate retelele sociale', '2026-09-02', 3, 8),
('Noul sediu va fi pe strada Florilor nr 5', '2026-10-03', 6, 3),
('Aplicatia are un bug ascuns. Rezolvati va rog', '2026-10-05', 1, 6),
('Au aplicat multi oameni pentru job; Programati interviuri!', '2026-11-05', 4, 8),
('Anunt important! Am gasit si inchiriat un nou sediu!', '2026-09-15', 6, 3);


--am adaugat tot tabelul 
INSERT INTO Sesiuni_Focus(durata_minute, data_sesiune, cod_utilizator)
VALUES 
(45, '2026-10-20 09:00:00', 1),
(60, '2026-10-20 10:15:00', 2),
(25, '2026-10-20 11:30:00', 3),
(90, '2026-10-20 13:00:00', 4),
(30, '2026-10-20 14:45:00', 5),
(120, '2026-10-21 09:30:00', 1),
(45, '2026-10-21 11:45:00', 2),
(60, '2026-10-21 13:15:00', 3),
(25, '2026-10-21 15:00:00', 4),
(50, '2026-10-21 16:10:00', 5);