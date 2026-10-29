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
        b dw 30
        c dd 200
        d dq 1000
; our code starts here
;(d+c) - (c+b) - (b+a)
segment code use32 class=code
    start:
        ; ...
            mov eax, dword [d]
            mov edx, dword [d+4]        ;EDX:EAX=d
            mov ebx, [c]
            mov ecx, 0                  ;ECX:EBX=c
            add eax, ebx
            adc edx, ecx                ;EDX:EAX=d+c
            mov ebx, [c]                ;EBX=c
            mov ecx, 0
            mov cx, [b]                 ;ECX=b
            add ebx, ecx                ;EBX=b+c
            mov ecx, 0                  
            sub eax, ebx                ;ECX:EBX=b+c
            sbb edx, ecx                ;EDX:EAX=(d+c)-(b+c)
            mov ebx, 0
            mov bx, [b]                 ;EBX=b
            mov ecx, 0
            mov cl, [a]                 ;ECX=a
            add ebx, ecx                ;EBX=b+a
            mov ecx, 0                  ;ECX:EBX=b+a
            sub eax, ebx
            sbb edx, ecx                ;EDX:EAX=(d+c)-(b+c)-(b+a)
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
