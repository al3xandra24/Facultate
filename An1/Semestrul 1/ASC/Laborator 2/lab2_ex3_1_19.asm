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
       c db 10
       d dw 20
; our code starts here
;[(a-b)*3+c*2]-d
segment code use32 class=code
    start:
        ; ...
            mov al, [a]     ;AL=a
            sub al, [b]     ;AL=a-b
            mov bl, 3       ;BL=3
            mul bl          ;AX=(a-b)*3
            mov bx, ax      ;BX=(a-b)*3
            mov al, [c]     ;AL=c
            mov cl, 2       ;CL=2
            mul cl          ;AX=c*2
            add ax, bx      ;AX=(a-b)*3+c*2
            sub ax, [d]     ;AX=[(a-b)*3+c*2]-d
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
