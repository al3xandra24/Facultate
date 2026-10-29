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
        a db 8
        b dw 40
        c dd 100
        d dq 110
; our code starts here
;(a + b + c) - (d + d) + (b + c)
segment code use32 class=code
    start:
        ; ...
            mov eax, 0
            mov al, [a]     ;EAX=a, AX=a
            add ax, [b]     ;EAX=a+b, AX=a+b
            add eax, [c]    ;EAX=a+b+c
            mov ebx, 0
            mov bx, [b]     ;EBX=b
            add ebx, [c]    ;EBX=b+c
            add eax, ebx    ;EAX=(a+b+c)+(b+c)
            mov edx, 0      ;EDX:EAX=(a+b+c)+(b+c)
            sub eax, dword [d]
            sbb edx, dword [d+4]
            sub eax, dword [d]
            sbb edx, dword [d+4]    ;EDX:EAX=(a+b+c)-(d+d)+(b+c)
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
