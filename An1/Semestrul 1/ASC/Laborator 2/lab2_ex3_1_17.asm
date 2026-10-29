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
        a db 5
        d dw 20
; our code starts here
;  300-[5*(d-2*a)-1]
segment code use32 class=code
    start:
        ; ...
            mov al, [a]     ;AL=a
            mov bl, 2       ;BL=2
            mul bl          ;AX=2*a
            mov bx, [d]     ;BX=d
            sub bx, ax      ;BX=d-2*a
            mov ax, 5       ;AX=5
            mul bx          ;DX:AX=5*(d-2*a)
            push dx
            push ax
            pop eax         ;EAX=5*(d-2*a)
            sub eax, 1      ;EAX=5*(d-2*a)-1
            mov ebx, 300
            sub ebx, eax    ;EBX=300-[5*(d-2*a)-1]
            mov eax, ebx    ;EAX=300-[5*(d-2*a)-1]
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
