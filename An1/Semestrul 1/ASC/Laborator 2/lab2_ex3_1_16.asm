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
        b db 10
        c db 12
; our code starts here
; (a+b)/2 + (10-a/c)+b/4
segment code use32 class=code
    start:
        ; ...
        mov al, [a]     ;AL=a
        add al, [b]     ;AL=a+b
        mov ah, 0       ;AX=a+b
        mov bl, 2       ;AX=a+b, BL=2
        div bl          ;AL=(a+b)/2
        mov bl, al      ;BL=(a+b)/2
        mov al, [a]     ;AL=a
        mov ah, 0       ;AX=a
        mov cl, [c]     ;CL=c
        div cl          ;AL=a/c
        add bl, 10      ;BL=(a+b)/2+10
        sub bl, al      ;BL=(a+b)/2+10-a/c
        mov al, [b]     ;AL=b
        mov ah, 0       ;AX=b
        mov cl, 4       ;CL=4
        div cl          ;AL=b/4
        add bl, al      ;BL=(a+b)/2+10-a/c+b/4
        mov al, bl      ;AL=(a+b)/2+10-a/c+b/4
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
