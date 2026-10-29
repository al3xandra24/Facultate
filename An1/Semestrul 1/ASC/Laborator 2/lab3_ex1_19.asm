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
        b dw 20
        c dd 200
        d dq 800
; our code starts here
;(d+d)-(a+a)-(b+b)-(c+c)
segment code use32 class=code
    start:
        ; ...
            mov eax, 0
            mov al, [a]     ;EAX=a, AX=a
            add al, [a]     ;EAX=a+a; AX=a+a
            add ax, [b]     ;AX=(a+a)+b
            add ax, [b]     ;AX=(a+a)+(b+b)
            add eax, [c]    ;EAX=(a+a)+(b+b)+c
            add eax, [c]    ;EAX=(a+a)+(b+b)+(c+c)
            mov ebx, eax    ;EBX=(a+a)+(b+b)+(c+c)
            mov ecx, 0      ;ECX:EBX=(a+a)+(b+b)+(c+c)
            mov eax, dword [d]
            mov edx, dword [d+4]    ;EDX:EAX=d
            add eax, dword [d]
            adc edx, dword [d+4]    ;EDX:EAX=d+d
            sub eax, ebx
            sbb edx, ecx            ;EAX:EDX=(d+d)-(a+a)-(b+b)-(c+c)
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
