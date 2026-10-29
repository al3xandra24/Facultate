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
        a db 6
        b db 4
        c db 5
        d dw 100
; our code starts here
;[(10+d)-(a*a-2*b)]/c
segment code use32 class=code
    start:
        ; ...
            mov al, [a]     ;AL=a
            mul al          ;AX=a*a
            mov bx, ax      ;BX=a*a
            mov al, [b]     ;AL=b
            mov cl, 2       ;CL=2
            mul cl          ;AX=2*b
            sub bx, ax      ;BX=a*a-2*b
            mov ax, 10      ;AX=10
            add ax, [d]     ;AX=10+d
            sub ax, bx      ;AX=(10+d)-(a*a-2*b)
            div byte [c]    ;AL=[(10+d)-(a*a-2*b)]/c
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
