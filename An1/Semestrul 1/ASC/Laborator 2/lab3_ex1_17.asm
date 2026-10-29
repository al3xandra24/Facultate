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
        a db 24
        b dw 10
        c dd 200
        d dq 150
; our code starts here
;(c+c-a)-(d+d)-b
segment code use32 class=code
    start:
        ; ...
            mov eax, [c]        ;EAX=c
            add eax, [c]        ;EAX=c+c
            mov ebx, 0
            mov bl, [a]         ;EBX=a
            sub eax, ebx        ;EAX=c+c-a
            mov edx, 0          ;EDX:EAX=c+c-a
            sub eax, dword [d]  
            sbb edx, dword [d+4] ;EDX:EAX=(c+c-a)-d
            sub eax, dword [d]
            sbb edx, dword [d+4] ;EDX:EAX=(c+c-a)-(d+d)
            mov ebx, 0
            mov bx, [b]          ;EBX=b
            mov ecx, 0           ;ECX:EBX=b
            sub eax, ebx
            sbb edx, ecx        ;EDX:EAX=(c+c-a)-(d+d)-b
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
