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
        d db 1
        c db 2
; our code starts here
;[(a-d)+b]*2/c
segment code use32 class=code
    start:
        ; ...
            mov al, [a]
            sub al, [d]
            add al, [b]
            mov bl, 2
            mul bl          ;AX=[(a-d)+b]*2
            div byte [c]    ;AL=[(a-d)+b]*2/c
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
