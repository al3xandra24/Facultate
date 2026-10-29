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
        b db 2
        c db 8
        d dw 20
; our code starts here
;[100-10*a+4*(b+c)]-d
segment code use32 class=code
    start:
        ; ...
            mov al, [b]     ;AL=b
            add al, [c]     ;AL=b+c
            mov cl, 4       ;CL=4
            mul cl          ;AX=4*(b+c)
            mov bx, ax      ;BX=4*(b+c)
            mov al, 10      ;AL=10
            mul byte [a]    ;AX=10*a
            sub bx, ax      ;BX=-10*a+4*(b+c)
            add bx, 100     ;BX=100-10*a+4*(b+c)
            sub bx, [d]     ;BX=[100-10*a+4*(b+c)]-d
            mov ax, bx      ;AX=[100-10*a+4*(b+c)]-d
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
