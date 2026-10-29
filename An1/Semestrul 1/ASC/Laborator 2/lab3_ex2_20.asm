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
        a db 12
        b dw 58
        c dd 680
        d dq 1018
; our code starts here
;a-b-(c-d)+d
segment code use32 class=code
    start:
        ; ...
            mov al, [a]                 ;AL=a
            cbw                         ;AX=a
            sub ax, [b]                 ;AX=a-b
            cwde                        ;EAX=a-b
            sub eax, [c]                ;EAX=a-b-c
            cdq                         ;EDX:EAX=a-b-c
            add eax, dword [d]
            adc edx, dword [d+4]        ;EDX:EAX=a-b-(c-d)
            add eax, dword [d]
            adc edx, dword [d+4]        ;EDX:EAX=a-b-(c-d)+d
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
