CREATE DATABASE ToDo;
GO
USE ToDo;

CREATE TABLE Utilizatori
(cod_utilizator INT PRIMARY KEY IDENTITY(1, 1),
nume NVARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE
);

CREATE TABLE Proiecte
(cod_proiect INT PRIMARY KEY IDENTITY(1, 1),
denumire NVARCHAR(100) NOT NULL, 
descriere NVARCHAR(200),
data_creare DATETIME
);

CREATE TABLE Sarcini
(cod_task INT PRIMARY KEY IDENTITY(1, 1),
denumire NVARCHAR(100) NOT NULL,
descriere NVARCHAR(250),
data_limita DATETIME,
cod_utilizator INT FOREIGN KEY REFERENCES Utilizatori(cod_utilizator),
cod_proiect INT FOREIGN KEY REFERENCES Proiecte(cod_proiect)
);

CREATE TABLE SarciniRecurente
(cod_task_rec INT PRIMARY KEY IDENTITY(1, 1),
denumire NVARCHAR(100) NOT NULL,
-- exemplu: frecventa_valoare=3 si frecventa_unitate_timp= Zi => Sarcina Recurenta trebuie sa se repete o daat la 3 zile
frecventa_valoare INT, 
frecventa_unitate_timp VARCHAR(30), 
este_activa BIT,
cod_utilizator INT FOREIGN KEY REFERENCES Utilizatori(cod_utilizator)
);

CREATE TABLE Membri_Proiect
(cod_proiect INT FOREIGN KEY REFERENCES Proiecte(cod_proiect),
cod_utilizator INT FOREIGN KEY REFERENCES Utilizatori(cod_utilizator),
rol_in_proiect VARCHAR(50),
PRIMARY KEY (cod_proiect, cod_utilizator)
);


CREATE TABLE Atasamente
(cod_atasament INT PRIMARY KEY IDENTITY(1, 1),
nume_fisier NVARCHAR(60) NOT NULL, 
dimensiune_in_MB REAL,
cale_fisier VARCHAR(100),
data_incarcare DATETIME,
cod_task INT FOREIGN KEY REFERENCES Sarcini(cod_task)
);

CREATE TABLE Comentarii(
cod_comentariu INT PRIMARY KEY IDENTITY,
text_comentariu NVARCHAR(600) NOT NULL, 
data_adaugare DATETIME,
cod_proiect INT FOREIGN KEY REFERENCES Proiecte(cod_proiect),
cod_utilizator INT FOREIGN KEY REFERENCES Utilizatori(cod_utilizator)
);

CREATE TABLE Categorii
(cod_categorie INT PRIMARY KEY IDENTITY,
 denumire VARCHAR(60),
);

CREATE TABLE SarciniCategorii
( cod_task INT FOREIGN KEY REFERENCES Sarcini(cod_task),
cod_categorie INT FOREIGN KEY REFERENCES Categorii(cod_categorie),
CONSTRAINT pk_SarciniCategorii PRIMARY KEY (cod_task, cod_categorie)
);

CREATE TABLE Sesiuni_Focus
(cod_sesiune INT PRIMARY KEY IDENTITY(1, 1),
durata_minute INT,
data_sesiune DATETIME,
cod_utilizator INT FOREIGN KEY REFERENCES Utilizatori(cod_utilizator)
);