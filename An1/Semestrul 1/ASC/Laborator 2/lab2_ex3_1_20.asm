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
        b db 4
        c db 2
        d dw 100
; our code starts here
;(50-b-c)*2+a*a+d
segment code use32 class=code
    start:
        ; ...
            mov al, 50      ;AL=50
            sub al, [b]     ;AL=50-b
            sub al, [c]     ;AL=50-b-c
            mov bl, 2       ;BL=2
            mul bl          ;AX=(50-b-c)*2
            mov bx, ax      ;BX=(50-b-c)*2
            mov al, [a]     ;AL=a
            mul al          ;AX=a*a
            add ax, bx      ;AX=(50-b-c)*2+a*a
            add ax, [d]     ;AX=(50-b-c)*2+a*a+d
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
