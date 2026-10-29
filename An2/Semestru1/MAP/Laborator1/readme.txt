

Principii SOLID pe care le-am avut în vedere în cadrul realizării diagramei UML și a programului:

	1. SRP - Single Responsability Priniciple => fiecare clasă are o responsabilitate clară-> există câte o clasă care implementează
fiecare din operațiile de adunare, scădere, înmulțire, împărțire. De asemenea, clasa Validator se ocupă doar cu
verificarea corectitudinii sintactice a expresiei primită în linia de comandă, clasa EvaluateExpression
calculează valoarea expresiei, iar clasa ComplexNumber se ocupă cu crearea unui tip de dată personalizat -ComplexNumber

    2. OCP - Open Closed Principle => Deoarece exista interfata Operation, orice noua operatie care este implementata nu schimba cu nimic
modalitatea de evaluare a expresiei cu ajutorul unei stive din EvaluateExpression

	3.LSP - Liskov Substitution Principle => orice clasă care implementeaza interfata Operation (Addition, Division, Multiplication,
Substraction) poate inlocui interfata

    4.ISP - Interface Segregation Principle => interfata Operation contine o singura metoda (execute_operation), motiv pentru care clasele
care implementeaza interfata nu sunt nevoite sa implementeze operatii de care nu au nevoie

    5.DIP - Dependency Inversion Principle => Clasa EvaluateExpression nu depinde in mod direct de implementarile din clasele Addition,
Substraction, Division, Multiplication, ci depind de interfata Operation

Sabloane de proiectare utilzate in cadrul diagramei UML:

1. Sablonul Factory - cu ajutorul acestui sablon se instantiaza clasele concrete Addition, Division, Multiplication, Substraction, Division
fara a expune tipul lor concret in logica principala. Astfel, se analizeaza operatorul, se construieste clasa specifica si se livreaza prin intermediul
interfetei abstracte Operation.

2. Sablonul Strategy - cu ajutorul acestui sablon am facut operatiile matematice sa fie executate fara sa conteze daca in spate se face o adunare/scadere/
inmultire/impartire