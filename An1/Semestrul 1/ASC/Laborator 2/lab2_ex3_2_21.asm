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
        d db 2
        e dw 10
        f dw 22
        g dw 15
        h dw 25
; our code starts here
;(f*g-a*b*e)/(h+c*d)
segment code use32 class=code
    start:
        ; ...
            mov ax, [f]      ;AX=f
            mul word [g]     ;DX:AX=f*g
            push dx
            push ax
            pop ebx          ;EBX=f*g
            mov al, [a]
            mul byte [b]     ;AX=a*b
            mul word [e]     ;DX:AX=a*b*e
            push dx
            push ax
            pop eax          ;EAX=a*b*e
            sub ebx, eax     ;EBX=f*g-a*b*e
            mov al, [c]
            mul byte [d]     ;AX=c*d
            add ax, [h]      ;AX=c*d+h
            mov cx, ax       ;CX=c*d+h
            push ebx
            pop ax
            pop dx           ;DX:AX=f*g-a*b*e
            div cx           ;AX=(f*g-a*b*e)/(h+c*d)
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
