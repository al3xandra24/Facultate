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
        a db 4
        b db 5
        c db 8
        d dw 200
; our code starts here
;d-[3*(a+b+2)-5*(c+2)]
segment code use32 class=code
    start:
        ; ...
            mov al, [a]     ;AL=a
            add al, [b]     ;AL=a+b
            add al, 2       ;AL=a+b+2
            mov bl, 3       ;BL=3
            mul bl          ;AX=3*(a+b+2)
            mov bx, ax      ;BX=3*(a+b+2)
            mov al, [c]     ;AL=c
            add al, 2       ;AL=c+2
            mov cl, 5       ;CL=5
            mul cl          ;AX=(c+2)*5
            sub bx, ax      ;BX=3*(a+b+2)-(c+2)*5
            mov ax, [d]     ;AX=d
            sub ax, bx      ;AX=d-[3*(a+b+2)-(c+2)*5]
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
