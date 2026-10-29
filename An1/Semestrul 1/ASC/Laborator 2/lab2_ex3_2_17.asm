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
        b db 6
        c db 5
        d db 2
        f dw 30
        g dw 100
        h dw 60
; our code starts here
;h/a + (2 + b) + f/d – g/c
segment code use32 class=code
    start:
        ; ...
            mov ax, [h]     ;AX=h
            div byte [a]    ;AL=h/a
            add al, 2       ;AL=h/a+2
            add al, [b]     ;AL=h/a+(2+b)
            mov bl, al      ;BL=h/a+(2+b)
            mov ax, [f]     ;AX=f
            div byte [d]    ;AL=f/d
            add bl, al      ;BL=h/a+(2+b)+f/d
            mov ax, [g]     ;AX=g
            div byte [c]    ;AL=g/c
            sub bl, al      ;BL=h/a+(2+b)+f/d-g/c
            mov al, bl      ;AL=h/a+(2+b)+f/d-g/c
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
