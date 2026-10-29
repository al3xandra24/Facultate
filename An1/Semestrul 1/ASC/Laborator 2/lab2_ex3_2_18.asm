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
        c db 6
        d db 8
        f dw 50
; our code starts here
;f+(c-2)*(3+a)/(d-4)
segment code use32 class=code
    start:
        ; ...
            mov al, [c]     ;AL=c
            sub al, 2       ;AL=c-2
            mov bl, [a]     ;BL=a
            add bl, 3       ;BL=a+3
            mul bl          ;AX=(c-2)*(a+3)
            mov bl, [d]     ;BL=d
            sub bl, 4       ;BL=d-4
            div bl          ;AL=(c-2)*(a+3)/(d-4)
            mov ah, 0       ;AX=(c-2)*(a+3)/(d-4)
            add ax, [f]     ;AX=f+(c-2)*(a+3)/(d-4)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
