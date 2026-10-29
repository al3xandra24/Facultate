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
        b dw 35
        c dd 360
        d dq 800
; our code starts here
;(d-b)-a-(b-c)
segment code use32 class=code
    start:
        ; ...
            mov ebx, dword [d]
            mov ecx, dword [d+4]        ;ECX:EBX=d
            mov ax, [b]
            cwde
            cdq                         ;EDX:EAX=b
            sub ebx, eax
            sbb ecx, edx                ;ECX:EBX=d-b
            mov al, [a]
            cbw
            cwde
            cdq                         ;EDX:EAX=a
            sub ebx, eax
            sbb ecx, edx                ;ECX:EBX=(d-b)-a
            mov ax, [b]
            cwde
            cdq                         ;EDX:EAX=b
            sub ebx, eax
            sbb ecx, edx                ;ECX:EBX=(d-b)-a-b
            mov eax, [c]
            cdq                         ;EDX:EAX=c
            add ebx, eax
            adc ecx, edx                ;ECX:EBX=(d-b)-a-(b-c)
            mov eax, ebx
            mov edx, ecx                ;EDX:EAX=(d-b)-a-(b-c)
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
