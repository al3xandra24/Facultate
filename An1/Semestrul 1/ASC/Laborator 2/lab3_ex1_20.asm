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
        b dw 20
        c dd 100
        d dq 600
; our code starts here
;(a+c)-b+a + (d-c)
segment code use32 class=code
    start:
        ; ...
        mov eax, 0
        mov al, [a]
        add al, [a]             ;EAX=a+a    , AX=a+a
        sub ax, [b]             ;AX=a+a-b,  ,EAX= a+a-b
        mov edx, 0
        add eax, dword [d]
        adc edx, dword [d+4]        ;EDX:EAX=(a+c)-b+a + (d-c)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
