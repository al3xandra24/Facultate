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
        a db 5
        b db 10
        c db 20
        d dw 50
; our code starts here
;200-[3*(c+b-d/a)-300]
segment code use32 class=code
    start:
        ; ...
            mov bl, [c]     ;BL=c
            add bl, [b]     ;BL=c+b
            mov ax, [d]     ;AX=d
            mov cl, [a]     ;CL=a
            div cl          ;AL=d/a
            sub bl, al      ;BL=c+b-d/a
            mov al, 3       ;AL=3
            mul bl          ;AX=3*(c+b-d/a)
            sub ax, 300     ;AX=3*(c+b-d/a)-300
            mov bx, 200     ;BX=200
            sub bx, ax      ;BX=200-[3*(c+b-d/a)-300]
            mov ax, bx      ;AX=200-[3*(c+b-d/a)-300]
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
