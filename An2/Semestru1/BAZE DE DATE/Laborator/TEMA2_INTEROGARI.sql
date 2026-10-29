--1)Toti utilizatorii care au sarcini in proiecte care au data de creare mai devreme de iulie 
SELECT DISTINCT  u.nume,
		p.denumire AS Nume_Proiect,
		p.data_creare AS Data_Creare_Proiect
FROM Utilizatori u
INNER JOIN Sarcini s ON s.cod_utilizator=u.cod_utilizator
INNER JOIN Proiecte p ON p.cod_proiect=s.cod_proiect WHERE p.data_creare<'2026-07-01';
--Utilizari: WHERE, DISTINCT(utilizatori distincti- unii utilizatori au mai multe sarcini in cadrul aceluiasi proiect)
-- interogare care foloseste date din mai mult de 2 tabele => Utilizatori, Sarcini, Proiecte  

--2)Toti utilizatorii care au mai mult de o sarcina recurenta care se repeta cu o frecventa mai mare decat anual
SELECT u.nume,
	  COUNT(sr.denumire) AS Numar_Sarcini_Recurente
FROM SarciniRecurente sr
INNER JOIN Utilizatori u ON u.cod_utilizator= sr.cod_utilizator
WHERE sr.frecventa_valoare > 0 AND sr.frecventa_unitate_timp IN ('Zi', 'Saptamana', 'Luna')
GROUP BY u.nume 
HAVING COUNT(sr.denumire)>1;
--Utilizari: WHERE, GROUP_BY, HAVING

--3) Cate sarcini sunt in fiecare categorie introdusa
SELECT c.denumire AS Denumire_Categorie,
	   COUNT(s.denumire) AS Numar_Sarcini
FROM Categorii c
INNER JOIN SarciniCategorii sc ON sc.cod_categorie=c.cod_categorie
INNER JOIN Sarcini s ON s.cod_task=sc.cod_task
GROUP BY c.denumire
--Utilizari: GROUP_BY
-- interogare care foloseste mai mult de 2 tabele(Categorii, Sarcini, SarciniCategorii)
-- interogare pe tabele care sunt in relatie m-n (Categorii, Sarcini)

--4)Utilizatorii care sunt membrii in mai mult de un proiect creat incepand cu anul 2025
SELECT u.nume AS Nume_Utilizator,
	   COUNT(p.cod_proiect) AS Numar_Proiecte
FROM Utilizatori u
INNER JOIN Membri_Proiect mp ON mp.cod_utilizator=u.cod_utilizator
INNER JOIN Proiecte p ON p.cod_proiect=mp.cod_proiect
WHERE p.data_creare>='2025-01-01'
GROUP BY u.nume
HAVING COUNT(p.cod_proiect)>1
--Utilizari: WHERE, GROUP BY, HAVING
--interogare care foloseste mai mult de doua tabele(Utilizatori, Proiecte, Membri_Proiect)
--interogare pe tabele care sunt in relatie m-n (Utilizatori, Proiecte)

--5) Toti utilizatorii care au mai mult de 90 minute de focus dupa data de 1 octombrie 2026
SELECT u.nume AS Nume_Utilizator,
	SUM(sf.durata_minute) AS Durata_minute
FROM Utilizatori u
INNER JOIN Sesiuni_Focus sf ON sf.cod_utilizator=u.cod_utilizator
WHERE sf.data_sesiune>'2026-10-01'
GROUP BY u.nume
HAVING SUM(sf.durata_minute)>90
--Utilizari: WHERE, GROUP_BY, HAVING

--6) Spatiul total ocupat de atasamente si numarul acestora per proiect.
SELECT p.denumire AS Nume_Proiect,
	COUNT(a.cod_atasament) AS Numar_Atasamente,
	SUM(a.dimensiune_in_MB) AS Dimensiune_totala_MB
FROM Proiecte P
LEFT OUTER JOIN Sarcini s ON p.cod_proiect=s.cod_proiect
LEFT OUTER JOIN Atasamente a ON s.cod_task=a.cod_task
GROUP BY p.denumire
--Utilizari: GROUP_BY
--interogare care foloseste mai mult de doua tabele(Proiecte, Atasamente, Sarcini)

--7) Comentariile lasate la proiectul 'Dezvoltare aplicatie mobila' si autorii acestor comentarii
SELECT u.nume AS Nume_Utilizator,
	   c.text_comentariu
FROM Comentarii c
INNER JOIN Utilizatori u ON u.cod_utilizator=c.cod_utilizator
INNER JOIN Proiecte p ON p.cod_proiect=c.cod_proiect
WHERE p.denumire='Dezvoltare aplicatie mobila'
--Utilizari: WHERE 
--interogare care foloseste mai mult de doua tabele(Comentarii, Utilizatori, Proiecte)

--8) Categoriile unice pe care le au sarcinile din proiectele care au fost lansate in 2026.
SELECT DISTINCT c.denumire AS Nume_Categorie
FROM Categorii c
INNER JOIN SarciniCategorii sc ON c.cod_categorie=sc.cod_categorie
INNER JOIN Sarcini s ON sc.cod_task=s.cod_task
INNER JOIN Proiecte p ON s.cod_proiect=p.cod_proiect
WHERE p.data_creare>='2026-01-01' AND p.data_creare<'2027-01-01'
--Utilizari: WHERE, DISTINCT 
--interogare care foloseste mai mult de 2 tabele (SarciniCategorii, Sarcini, Proiecte, Categorii)
--interogare ce foloseste doua tabele aflate in relatie m-n (Sarcini, Categorii)

--9) Echipa detaliata a fiecarui proiect 
SELECT p.denumire AS Nume_Proiect,
		u.nume,
		mp.rol_in_proiect
FROM Proiecte p
INNER JOIN Membri_Proiect mp ON mp.cod_proiect=p.cod_proiect
INNER JOIN Utilizatori u ON u.cod_utilizator=mp.cod_utilizator
ORDER BY p.denumire, u.nume
--Utilizari: interogare care foloseste mai mult de 2 tabele (Proiecte, Membri_Proiect, Utilizatori)
--interogare ce foloseste doua tabele aflate in relatie m-n (Proiecte, Utilizatori)

--10) Utilizatori care au mai mult de o sarcina alocata 
SELECT u.nume,
		COUNT (s.cod_task) AS Numar_Sarcini_Alocate
FROM Utilizatori u
INNER JOIN Sarcini s ON u.cod_utilizator=s.cod_utilizator
GROUP BY u.nume 
HAVING COUNT(s.cod_task)>1
--Utilizari: GROUP BY, HAVING 

--WHERE (1, 2, 4, 5, 7, 8) - 6 utilizari 
--GROUP BY (2, 3, 4, 5, 6, 10) - 6 utilizari
--DISTINCT (1, 8)- 2 utilizari 
--HAVING (2, 4, 5, 10)- 4 utilizari 
--Interogari care extrag informatii din cel putin 3 tabele (1, 3, 4, 6, 7, 8, 9)- 7 utilizari
--Interogari care extrag informatii din tabele aflate in relatie m-n (3, 4, 8)- 3 utilizari 






