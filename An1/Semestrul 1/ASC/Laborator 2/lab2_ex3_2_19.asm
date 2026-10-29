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
    a db 2
    b db 5
    c db 8
    e dw 20
    f dw 15
    g dw 16
    h dw 40
; our code starts here
;(e + g) * 2 / (a * c) + (h – f) + b * 3
segment code use32 class=code
    start:
        ; ...
            mov ax, [e]     ;AX=e
            add ax, [g]     ;AX=e+g
            mov bx, 2       ;BX=2
            mul bx          ;DX:AX=(e+g)*2
            push dx
            push ax
            pop eax         ;EAX=(e+g)*2
            mov ebx, eax    ;EBX=(e+g)*2
            mov al, [a]     ;AL=a
            mov cl, [c]     ;CL=c
            mul cl          ;AX=a*c
            mov cx, ax      ;CX=a*c
            push ebx
            pop ax
            pop dx          ;DX:AX=(e+g)*2
            div cx          ;AX=(e+g)*2/(a*c)
            add ax, [h]     ;AX=(e+g)*2/(a*c)+h
            sub ax, [f]     ;AX=(e+g)*2/(a*c)+h-f
            mov bx, ax
            mov al, 3
            mov cl, [b]
            mul cl          ;AX=b*3
            add bx, ax      ;BX=(e+g)*2/(a*c)+h-f+b*3
            mov ax, bx      ;AX=(e+g)*2/(a*c)+h-f+b*3
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
