RETEA DE SOCIALIZARE - Laborator 3

Aplicatie Java tip consola pentru gestionarea utilizatorilor si a prieteniilor dintre acestia.
Arhitectura stratificata: Domain -> Validator -> Repository -> Service -> UI


* MODUL DE FUNCTIONARE FACTORY

In fisierul resources/config.properties se regaseste configurarea (locul in care se stocheaza datele -MEMORY, FILE, DATABASE), cat si
informatiile necesare fiecarui tip de Repository creat (FILE- fisierele de unde se citesc utilizatorii si prieteniile dintre acestia,
DATABASE- url-ul, user-ul si password-ul bazei de date
In Main se citeste acest fisier si se transmite mai departe catre clasa RepositoryFactory, care este singurul loc din aplicatie care cunoaste
clasele concrete din repository. Aceasta clasa are cate o metoda pentru crearea fiecarui tip de repository:
  -createUserRepository -> Repository<Long, User>
  -createFriendshipRepository -> Repository<Tuple<Long, Long>, Friendship>
Cele doua repository-uri sunt create intotdeauna pentru acelasi tip de persistenta
    MEMORY   -> InMemoryRepository
    FILE     -> UserFileRepository, FriendshipFileRepository
    DATABASE -> UserDBRepository, FriendshipDBRepository


* SCHIMBARI NECESARE PENTRU TRECEREA LA BAZE DE DATE

1. crearea tabelelor users si friendships in baza de date
2. implementarea metodelor din UserDBRepository si FriendshipDBRepository - acum acestea arunca doar UnsupportedOperationExcception
3. se schimba in config.properties tipul de repository in DATABASE


* GESTIONARE A FISIERULUI LIPSA/ A LINIILOR CORUPTE

-Fisier inexistent=> Aplicatia porneste cu repository-ul gol. Fisierul (si folderul data, daca lipseste), sunt create la prima adaugare.
- Linie corupta/invalida => Linia este raportata si ignorata, iar incarcarea continua. Am ales aceasta varianta pentru ca o linie gresita
nu ar trebui sa faca inutilizabile toate celelalte date.
Sunt tratate ca linii gresite: un ID care nu este numar, o data care nu respecta formatul ISO, o linie careia ii lipsesc campuri,
date respinse de validator, o prietenie duplicata.
! Stergerea unui utilizator atrage si stergerea prieteniilor acestuia.


* PRINCIPII SOLID REGASITE IN PROIECT

     - SRP (Single Responsibility Principle) - Fiecare clasa are o singura responsabilitate:
         -User, Friendship, Tuple -> datele(Domain)
         -UserValidator, FriendshipValidator                       : validarea
         - repository-urile                                        : stocarea datelor
         - UserService, FriendshipService, CommunityService        : logica aplicatiei
         - UI                                                      : interactiunea cu utilizatorul
         - RepositoryFactory                                       : alegerea si crearea repository-urilor

     -OCP (Open/ Closed Principle)=> Aplicatia este deschisa la extindere, inchisa la modificare:
     Exemplu-> un nou tip de persistenta(baza de date) se adauga prin clase noi si prin adaugarea unui caz nou in RepositoryFactory, nu
            prin modificarea logicii aplicatiei

     -LSP - Liskov Substitution Principle -> Repository-urile FriendshipRepository, UserRepository si InMemoryRepository pot inlocui oriunde
     tipul Repository, pentru ca respecta aceleasi condidii

     -DIP - Dependency Inversion Principle -> Modulele de nivel inalt depind de abstractizari, nu de clase concrete
        - service-urile primesc prin constructor Repository<ID, E> (interfata)


* SABLOANE REGASITE IN PROIECT
Factory (Simple Factory)-> RepositoryFactory.
  Creeaza repository-ul potrivit dupa repository.type din configurare si il intoarce ca tip abstract Repository<ID, E>.

Template Method -> FileAbstractRepository.
  Metodele loadData() si saveData() definesc pasii ficsi ai algoritmului (deschiderea fisierului, citirea linie cu linie, tratarea liniilor gresite,
  scrierea tuturor entitatilor). Pasii care difera de la o entitate la alta sunt metodele abstracte extractEntity (linie -> entitate) si
  createEntityasString (entitate -> linie).

Strategy -> interfata Validator<T> cu UserValidator si FriendshipValidator.
  Repository-ul primeste strategia de validare prin constructor si apeleaza validator.validate(entity) fara sa stie ce reguli se aplica.

Decorator -> FileAbstractRepository (citirea si scrierea fisierelor) si Main (citirea configurarii).
  BufferedReader decoreaza un FileReader, iar BufferedWriter un FileWriter.

