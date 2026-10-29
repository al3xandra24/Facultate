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
        a db 20
        b dw 40
        c dd 80
        d dq 100
; our code starts here
;((a + a) + (b + b) + (c + c)) - d
segment code use32 class=code
    start:
        ; ...
            mov eax, 0
            mov al, [a]
            add al, [a]         ;EAX=a+a, AX=a+a, AL=a+a
            add ax, [b]
            add ax, [b]         ;EAX=a+a+b+b, AX=a+a+b+b
            add eax, [c]
            add eax, [c]        ;EAX=(a+a)+(b+b)+(c+c)
            mov edx, 0          ;EDX:EAX=(a+a)+(b+b)+(c+c)
            sub eax, dword [d]
            sbb edx, dword [d+4]    ;EDX:EAX=(a+a)+(b+b)+(c+c)-d
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
