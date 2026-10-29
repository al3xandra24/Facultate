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
        a db 10
        b dw 40
        c dd 120
        d dq 400
; our code starts here
;(d+d)-a-b-c
segment code use32 class=code
    start:
        ; ...
            mov eax, dword [d]
            mov edx, dword [d+4]
            add eax, dword [d]
            adc edx, dword [d+4]        ;EDX:EAX=d+d
            mov ebx, 0
            mov bl, [a]                 ;EBX=a, BX=a
            add bx, [b]                 ;BX=a+b; EBX=a+b
            add ebx, [c]                ;EBX=a+b+c
            mov ecx, 0                  ;ECX:EBX=a+b+c
            sub eax, ebx
            sbb edx, ecx                ;EAX:EDX=(d+d)-(a+b+c)
            
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
