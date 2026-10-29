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
        b dw 56
        c dd 320
        d dq 914
; our code starts here
;(d+a)-(c-b)-(b-a)+(c+d)
segment code use32 class=code
    start:
        ; ...
            mov ebx, dword [d]
            mov ecx, dword [d+4]        ;ECX:EBX=d
            mov al, [a]
            cbw
            cwde
            cdq                         ;EDX:EAX=a
            add ebx, eax
            adc ecx, edx                ;ECX:EBX=d+a
            mov ax, [b]
            cwde                        ;EAX=b
            mov edx, [c]
            sub edx, eax                ;EDX=c-b
            mov eax, edx
            cdq                         ;EDX:EAX=c-b
            sub ebx, eax
            sbb ecx, edx                ;ECX:EBX=(d+a)-(c-b)
            mov al, [a]
            cbw                         ;AX=a
            mov dx, [b]
            sub dx, ax                  ;DX=b-a
            mov ax, dx                  ;AX=b-a
            cwde
            cdq                         ;EDX:EAX=b-a
            sub ebx, eax
            sbb ecx, edx                ;ECX:EAX=(d+a)-(c+b)-(b-a)
            add ebx, dword [d]
            adc ecx, dword [d+4]        ;ECX:EBX=(d+a)-(c+b)-(b-a)+d
            mov eax, [c]
            cdq                         ;EDX:EAX=c
            add eax, ebx
            adc edx, ecx                ;EDX:EAX=(d+a)-(c+b)-(b-a)+d+c
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
