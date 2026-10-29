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
        a db 29
        b dw 100
        c dd 320
; our code starts here
;c-a-(b+a)+c
segment code use32 class=code
    start:
        ; ...
            mov eax, [c]        ;EAX=c
            mov ebx, 0
            mov bl, [a]         ;EBX=a
            sub eax, ebx        ;EAX=c-a
            mov ebx, 0
            mov bx, [b]         ;EBX=b
            sub eax, ebx        ;EAX=c-a-b
            mov ebx, 0
            mov bl, [a]         ;EBX=a
            sub eax, ebx        ;EAX=c-a-(b+a)
            add eax, [c]        ;EAX=c-a-(a+b)+c
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
