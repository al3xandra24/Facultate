bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
        a db 2
        b db 3
        c db 5
        d dw 160
; our code starts here
;(10*a-5*b)+(d-5*c)
segment code use32 class=code
    start:
        ; ...
            mov al, 10      ;AL=10
            mul byte [a]    ;AX=10*a
            mov bx, ax      ;BX=10*a
            mov al, 5       ;AL=5
            mul byte [b]    ;AX=5*b
            sub bx, ax      ;BX=10*a-5*b
            add bx, [d]     ;BX=10*a-5*b+d
            mov al, 5       ;AL=5
            mul byte [c]    ;AX=5*c
            sub bx, ax      ;BX=10*a-5*b+(d-5*c)
            mov ax, bx
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
