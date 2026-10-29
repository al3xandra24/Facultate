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
        c db 10
        g dw 20
; our code starts here
;[(a+b+c)*2]*3/g
segment code use32 class=code
    start:
        ; ...
            mov al, [a]     ;AL=a
            add al, [b]     ;AL=a+b
            add al, [c]     ;AL=a+b+c
            mov bl, 2       ;BL=2
            mul bl          ;AX=(a+b+c)*2
            mov bx, 3       ;BX=3
            mul bx          ;DX:AX=[(a+b+c)*2]*3
            mov bx, [g]
            div bx          ;AX=[(a+b+c)*2]*3/g
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
