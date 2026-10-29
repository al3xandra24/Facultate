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
        c dd 200
        d dq 1000
; our code starts here
;(c+d-a)-(d-c)-b
segment code use32 class=code
    start:
        ; ...
            mov eax, [c]
            cdq                     ;EDX:EAX=c
            add eax, dword [d]
            adc edx, dword [d+4]    ;EDX:EAX=c+d
            mov ebx, eax
            mov ecx, edx            ;ECX:EBX=c+d
            mov al, [a]
            cbw
            cwde
            cdq                     ;EDX:EAX=a
            sub ebx, eax            
            sbb ecx, edx            ;ECX:EBX=c+d-a
            sub ebx, dword [d]
            sbb ecx, dword [d+4]    ;ECX:EBX=c+d-a-d
            mov eax, [c]
            cdq                     ;EDX:EAX=c
            add ebx, eax
            adc ecx, edx            ;ECX:EBX=c+d-a-(d-c)
            mov ax, [b]
            cwde
            cdq                     ;EDX:EAX=b
            sub ebx, eax
            sbb ecx, edx            ;ECX:EBX=c+d-a-(d-c)-b
            mov eax, ebx
            mov edx, ecx            ;EDX:EAX=c+d-a-(d-c)-b
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
